package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.CreateRiderDTO;
import com.deliverysl.luxurydelivery.user.dto.RiderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

@Tag(name = "Rider", description = "Operaciones CRUD sobre riders")
public interface RiderControllerSwagger {

    @Operation(
            summary = "Crear un rider",
            description = "Crea un nuevo rider y devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RiderDTO.class))
            )
    })
    ResponseEntity<RiderDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación del empleado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateRiderDTO.class))
            )
            @Valid CreateRiderDTO createRiderDTO
    );

    @Operation(
            summary = "Editar un rider",
            description = "Actualiza un rider existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RiderDTO.class))
            )
    })
    ResponseEntity<RiderDTO>edit(
            @RequestBody(
                    required = true,
                    description = "Datos de edición del rider",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateRiderDTO.class))
            )
            @Valid CreateRiderDTO createRiderDTO,
            @Parameter(description = "Identificador del rider", example = "1")
            Long id
    );
}
