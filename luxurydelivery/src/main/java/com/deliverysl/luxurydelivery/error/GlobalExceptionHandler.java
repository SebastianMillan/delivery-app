package com.deliverysl.luxurydelivery.error;

import com.deliverysl.luxurydelivery.allergen.exception.AllergenNotFoundException;
import com.deliverysl.luxurydelivery.category.exception.CategoryNotFoundException;
import com.deliverysl.luxurydelivery.order.exception.OrderNotFoundException;
import com.deliverysl.luxurydelivery.orderline.exception.OrderlineNotFoundException;
import com.deliverysl.luxurydelivery.product.exception.ProductNotFoundException;
import com.deliverysl.luxurydelivery.restaurant.exception.ProtectedRestaurantException;
import com.deliverysl.luxurydelivery.restaurant.exception.RestaurantNotFoundException;
import com.deliverysl.luxurydelivery.type.exception.ProtectedTypeException;
import com.deliverysl.luxurydelivery.type.exception.TypeNotFoundException;
import com.deliverysl.luxurydelivery.user.exception.PasswordNotMatchException;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${app.error.base-url}")
    //Dejo esto comentado porque si no, no me funciona en mi pc
    //@Value("${app.error.base-url:http://localhost:8080}")
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

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpServletRequest req){
        log.warn("400 Bad Request {} - {}",req.getRequestURI(),ex.getMessage());

        //Extrae errores campo a campo
        Map<String, List<String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField, //Nombre del campo
                        Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())//Mensaje de error
                ));

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Invalid data");
        problem.setDetail("Validation errors found in the request body");
        problem.setProperty("fields",fieldErrors);
        enrich(problem,req,"request.validation-error");
        return problem;

    }


    @ExceptionHandler(RestaurantNotFoundException.class)
    public ProblemDetail handleRestaurantNotFound(RestaurantNotFoundException ex, HttpServletRequest req) {
        log.warn("404 Not Found {} - {}", req.getRequestURI(), ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Restaurant not found");
        problem.setDetail(ex.getMessage());
        enrich(problem, req, "restaurant.not-found");
        return problem;
    }

    @ExceptionHandler(ProtectedRestaurantException.class)
    public ProblemDetail handleProtectedRestaurant(ProtectedRestaurantException ex, HttpServletRequest req){
        log.warn("403 Forbidden {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problem.setTitle("Protected restaurant");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"restaurant.protected-delete-forbidden");

        return problem;
    }

    @ExceptionHandler(TypeNotFoundException.class)
    public ProblemDetail handleTypeNotFound(TypeNotFoundException ex,HttpServletRequest req){
        log.warn("404 Not Found {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Type not found");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"type.not-found");
        return problem;

    }

    @ExceptionHandler(ProtectedTypeException.class)
    public ProblemDetail handleProtectedType(ProtectedTypeException ex, HttpServletRequest req){
        log.warn("403 Forbidden {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problem.setTitle("Protected type");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"type.protected-delete-forbidden");

        return problem;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex,HttpServletRequest req){
        log.warn("404 Not Found {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("User not found");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"user.not-found");
        return problem;

    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ProblemDetail handlePasswordNotMatch(PasswordNotMatchException ex,HttpServletRequest req){
        log.warn("400 Bad Request {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Password and ConfirmPassword do not match");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"password.do-not-match");

        return problem;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(ProductNotFoundException ex,HttpServletRequest req){

        log.warn("404 Not Found {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Product not found");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"product.not-found");

        return problem;
    }

    @ExceptionHandler(OrderlineNotFoundException.class)
    public ProblemDetail handleOrderlineNotFound(OrderlineNotFoundException ex, HttpServletRequest req){

        log.warn("404 Not Found {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Orderline not found");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"Orderline.not-found");

        return problem;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ProblemDetail handleOrderNotFound(OrderNotFoundException ex, HttpServletRequest req){

        log.warn("404 Not Found {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Order not found");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"Order.not-found");

        return problem;
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ProblemDetail handleCategoryNotFound(CategoryNotFoundException ex, HttpServletRequest req){

        log.warn("404 Not Found {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Category not found");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"Category.not-found");

        return problem;
    }

    @ExceptionHandler(AllergenNotFoundException.class)
    public ProblemDetail handleAllergenNotFound(AllergenNotFoundException ex, HttpServletRequest req){

        log.warn("404 Not Found {} - {}",req.getRequestURI(),ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Allergen not found");
        problem.setDetail(ex.getMessage());
        enrich(problem,req,"Allergen.not-found");

        return problem;
    }

    private void enrich(ProblemDetail problem, HttpServletRequest req, String code) {
        problem.setType(URI.create(errorBaseUrl + URI.create(req.getRequestURI())));
        problem.setInstance(URI.create(req.getRequestURI()));
        problem.setProperty("timestamp", OffsetDateTime.now());
        problem.setProperty("code", code);
    }
}