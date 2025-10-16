package com.deliverysl.luxurydelivery.error;

import com.deliverysl.luxurydelivery.restaurant.exception.RestaurantNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${app.error.base-url}")
    private String errorBaseUrl;

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ProblemDetail handleUnexpected(Exception ex, HttpServletRequest req) {
        log.error("Error inesperado en {}: {}", req.getRequestURI(), ex.getMessage(), ex);

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Error interno del servidor");
        problem.setDetail("Ha ocurrido un error inesperado. Inténtalo más tarde.");
        enrich(problem, req, "error_interno");
        return problem;
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ProblemDetail handleRestaurantNotFound(RestaurantNotFoundException ex, HttpServletRequest req) {
        log.warn("404 Not Found {} - {}", req.getRequestURI(), ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Restaurante no encontrado");
        problem.setDetail(ex.getMessage());
        enrich(problem, req, "restaurant.not-found");
        return problem;
    }

    private void enrich(ProblemDetail problem, HttpServletRequest req, String code) {
        problem.setType(URI.create(errorBaseUrl + URI.create(req.getRequestURI())));
        problem.setInstance(URI.create(req.getRequestURI()));
        problem.setProperty("timestamp", OffsetDateTime.now());
        problem.setProperty("code", code);
    }
}
