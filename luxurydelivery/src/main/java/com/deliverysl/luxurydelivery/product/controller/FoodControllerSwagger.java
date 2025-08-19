package com.deliverysl.luxurydelivery.product.controller;

import com.deliverysl.luxurydelivery.product.dto.CreateFoodDTO;
import com.deliverysl.luxurydelivery.product.dto.FoodDTO;
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

@Tag(name = "Comidas", description = "Operaciones CRUD sobre alimentos")
public interface FoodControllerSwagger {

    @Operation(
            summary = "Crear un alimento",
            description = "Crea un nuevo alimento y devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Petición inválida (validación)"),
            @ApiResponse(responseCode = "409", description = "Conflicto (duplicado u otras restricciones)")
    })
    ResponseEntity<FoodDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación del alimento",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateFoodDTO.class))
            )
            @Valid CreateFoodDTO createFoodDTO
    );

    @Operation(
            summary = "Editar un alimento",
            description = "Actualiza un alimento existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Petición inválida (validación)"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto")
    })
    ResponseEntity<FoodDTO> edit(
            @Parameter(description = "Identificador del alimento", example = "1")
            Long id,
            @RequestBody(
                    required = true,
                    description = "Datos a actualizar del alimento",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateFoodDTO.class))
            )
            @Valid CreateFoodDTO createFoodDTO
    );
}
