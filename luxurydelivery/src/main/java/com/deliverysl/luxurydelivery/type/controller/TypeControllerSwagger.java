package com.deliverysl.luxurydelivery.type.controller;

import com.deliverysl.luxurydelivery.restaurant.dto.CreateRestaurandDTO;
import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
import com.deliverysl.luxurydelivery.type.dto.TypeCreateDTO;
import com.deliverysl.luxurydelivery.type.dto.TypeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Tipos", description = "Operaciones CRUD sobre tipos")
public interface TypeControllerSwagger {

    @Operation(
            summary = "Listado de tipos",
            description = "Devuelve todos los tipos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TypeDTO.class)))
            ),
    })
    ResponseEntity<List<TypeDTO>> findAll();

    @Operation(
            summary = "Detalle de un tipo",
            description = "Recupera un tipo por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo encontrado",
                    content = @Content(schema = @Schema(implementation = TypeDTO.class))
            )
    })
    ResponseEntity<TypeDTO> findById(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id
    );

    @Operation(
            summary = "Crear un tipo",
            description = "Crea un nuevo tipo. Devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = TypeDTO.class))
            ),
    })
    ResponseEntity<TypeDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación del tipo",
                    content = @Content(schema = @Schema(implementation = TypeCreateDTO.class))
            )
            @Valid TypeCreateDTO typeCreateDTO
    );

    @Operation(
            summary = "Editar un tipo",
            description = "Actualiza un tipo existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = TypeDTO.class))
            ),
    })
    ResponseEntity<TypeDTO> edit(
            @Parameter(description = "Identificador del tipo", example = "1")
            Long id,
            @RequestBody(
                    required = true,
                    description = "Datos a actualizar del tipo",
                    content = @Content(schema = @Schema(implementation = TypeCreateDTO.class))
            )
            @Valid TypeCreateDTO typeCreateDTO
    );

    @Operation(
            summary = "Desactiva un tipo",
            description = "Desactiva un tipo por su identificador. Responde 204 si se desactiva.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?>delete(
            @Parameter(description = "Identificador del tipo", example = "1")
            Long id
    );

    @Operation(
            summary = "Activa un tipo",
            description = "Activa un tipo desactivado. Devuelve el recurso activado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = TypeDTO.class))
            ),
    })
    ResponseEntity<TypeDTO>activate(
            @Parameter(description = "Identificador del tipo", example = "1")
            Long id
    );

    @Operation(
            summary = "Listado de tipos activos",
            description = "Devuelve todos los tipos activos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TypeDTO.class)))
            ),
    })
    ResponseEntity<List<TypeDTO>> findByActivateTrue();

    @Operation(
            summary = "Listado de tipos desactivados",
            description = "Devuelve todos los tipos desactivados. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TypeDTO.class)))
            ),
    })
    ResponseEntity<List<TypeDTO>> findByActivateFalse();
}
