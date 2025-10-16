package com.deliverysl.luxurydelivery.category.controller;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.dto.CategoryDTO;
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

@Tag(name = "Categorias", description = "Operaciones CRUD sobre categorías")
public interface CategoryControllerSwagger {

    @Operation(
            summary = "Listado de categorías",
            description = "Devuelve todas las categorías. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))
            ),
    })
    ResponseEntity<List<CategoryDTO>> findAll();

    @Operation(
            summary = "Detalle de una categoría",
            description = "Recupera una categoría por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría encontrado",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))
            )
    })
    ResponseEntity<CategoryDTO> findById(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id
    );

    @Operation(
            summary = "Crear una categoría",
            description = "Crea una nueva categoría. Devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))
            ),
    })
    ResponseEntity<CategoryDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación de la categoría",
                    content = @Content(schema = @Schema(implementation = CategoryCreateDTO.class))
            )
            @Valid CategoryCreateDTO categoryCreateDTO
    );

    @Operation(
            summary = "Editar una categoría",
            description = "Actualiza una categoría existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))
            ),
    })
    ResponseEntity<CategoryDTO> edit(
            @Parameter(description = "Identificador de la categoría", example = "1")
            Long id,
            @RequestBody(
                    required = true,
                    description = "Datos a actualizar del alérgeno",
                    content = @Content(schema = @Schema(implementation = CategoryCreateDTO.class))
            )
            @Valid CategoryCreateDTO categoryCreateDTO
    );

    @Operation(
            summary = "Activa o desactiva una categoría",
            description = "Activa o desactiva una categoría. Responde 204 si se activa o desactiva.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?> deactivate(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id
    );

    /*@Operation(
            summary = "Activa o desactiva una categoría",
            description = "Activa o desactiva una categoría. Devuelve el recurso activado o desactivado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = CategoryDTO.class))
            ),
    })
    ResponseEntity<CategoryDTO>toggle(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id
    );*/

    @Operation(
            summary = "Listado de categorías activas",
            description = "Devuelve todas las categorías activos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))
            ),
    })
    ResponseEntity<List<CategoryDTO>> findAllByActiveTrue();

}
