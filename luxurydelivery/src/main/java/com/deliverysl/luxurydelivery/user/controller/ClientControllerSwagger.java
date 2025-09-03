package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.ClientDTO;
import com.deliverysl.luxurydelivery.user.dto.CreateClientDTO;
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

@Tag(name = "Cliente", description = "Operaciones CRUD sobre clientes")
public interface ClientControllerSwagger {

    @Operation(
            summary = "Crear un cliente",
            description = "Crea un nuevo cliente y devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))
            )
    })
    ResponseEntity<ClientDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación del empleado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateClientDTO.class))
            )
            @Valid CreateClientDTO createClientDTO
    );

    @Operation(
            summary = "Editar un cliente",
            description = "Actualiza un cliente existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))
            )
    })
    ResponseEntity<ClientDTO>edit(
            @RequestBody(
                    required = true,
                    description = "Datos de edición del cliente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateClientDTO.class))
            )
            @Valid CreateClientDTO createClientDTO,
            @Parameter(description = "Identificador del cliente", example = "1")
            Long id
    );

}
