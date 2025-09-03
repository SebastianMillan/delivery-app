package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.AdminDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateAdminDTO;
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

@Tag(name = "Administradores", description = "Operaciones CRUD sobre administradores")
public interface AdminControllerSwagger {

    @Operation(
            summary = "Crear un administrador",
            description = "Crea un nuevo administrador y devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminDTO.class))
            )
    })
    ResponseEntity<AdminDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación del administrador",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateAdminDTO.class))
            )
            @Valid CreateAdminDTO createAdminDTO
    );

    @Operation(
            summary = "Editar un administrador",
            description = "Actualiza un administrador existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminDTO.class))
            )
    })
    ResponseEntity<AdminDTO> edit(
            @RequestBody(
            required = true,
            description = "Datos de edición del administrador",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateAdminDTO.class))
    )
            @Valid CreateAdminDTO createAdminDTO,
            @Parameter(description = "Identificador de la bebida", example = "1")
            Long id
    );
}
