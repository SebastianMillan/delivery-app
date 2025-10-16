package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Usuarios", description = "Operaciones de consulta y eliminación de usuarios")
public interface UserControllerSwagger {

    @Operation(
            summary = "Listado de usuarios",
            description = "Devuelve todos los usuarios. Si no hay resultados, responde 204 (sin contenido).",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))
                    )
            )
    })
    ResponseEntity<List<UserDTO>> findAll();

    @Operation(
            summary = "Detalle de un usuario",
            description = "Recupera un usuario por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)
                    )
            )
    })
    ResponseEntity<UserDTO> findById(
            @Parameter(description = "Identificador del producto", example = "1")
            Long id
    );

    @Operation(
            summary = "Activa o desactiva un usuario",
            description = "Activa o desactiva un usuario por su identificador. Devuelve 204 si se activa o desactiva correctamente.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?>deactive(
            @Parameter(description = "Identificador del producto", example = "1")
            Long id
    );

    /*@Operation(
            summary = "Activa o desactiva un usuario",
            description = "Activa o desactiva un usuario. Devuelve el recurso activado o desactivado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = UserDTO.class))
            ),
    })
    ResponseEntity<UserDTO> toggle(
            @Parameter(description = "Identificador del producto", example = "1")
            Long id
    );*/

    @Operation(
            summary = "Listado de usuarios activos",
            description = "Devuelve todos los usuarios activos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
            ),
    })
    ResponseEntity<List<UserDTO>> findAllByActivate();

}
