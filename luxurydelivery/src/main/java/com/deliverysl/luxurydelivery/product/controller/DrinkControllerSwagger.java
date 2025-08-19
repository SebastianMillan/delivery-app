package com.deliverysl.luxurydelivery.product.controller;

import com.deliverysl.luxurydelivery.product.dto.CreateDrinkDTO;
import com.deliverysl.luxurydelivery.product.dto.DrinkDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Bebidas", description = "Operaciones CRUD sobre bebidas")
public interface DrinkControllerSwagger {

    @Operation(
            summary = "Crear una bebida",
            description = "Crea una nueva bebida y devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DrinkDTO.class))
            )
    })
    ResponseEntity<DrinkDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación de la bebida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateDrinkDTO.class))
            )
            @Valid CreateDrinkDTO createDrinkDTO
    );

    @Operation(
            summary = "Editar una bebida",
            description = "Actualiza una bebida existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DrinkDTO.class))
            )
    })
    ResponseEntity<DrinkDTO> edit(
            @Parameter(description = "Identificador de la bebida", example = "1")
            Long id,
            @RequestBody(
                    required = true,
                    description = "Datos a actualizar de la bebida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateDrinkDTO.class))
            )
            @Valid CreateDrinkDTO createDrinkDTO
    );
}
