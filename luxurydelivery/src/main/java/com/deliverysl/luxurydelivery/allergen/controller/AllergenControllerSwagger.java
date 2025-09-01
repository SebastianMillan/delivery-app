package com.deliverysl.luxurydelivery.allergen.controller;

import com.deliverysl.luxurydelivery.allergen.dto.AllergenDTO;
import com.deliverysl.luxurydelivery.allergen.dto.CreateAllergenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Alergenos", description = "Operaciones CRUD sobre alérgenos")
public interface AllergenControllerSwagger {

    @Operation(
            summary = "Listado de alérgenos",
            description = "Devuelve todos los alérgenos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AllergenDTO.class)))
            ),
    })
    ResponseEntity<List<AllergenDTO>> findAll();

    @Operation(
            summary = "Detalle de un alérgeno",
            description = "Recupera un alérgeno por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Alérgeno encontrado",
                    content = @Content(schema = @Schema(implementation = AllergenDTO.class))
            )
    })
    ResponseEntity<AllergenDTO> findById(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id
    );

    @Operation(
            summary = "Crear un alérgeno",
            description = "Crea un nuevo alérgeno. Devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = AllergenDTO.class))
            ),
    })
    ResponseEntity<AllergenDTO> create(
            @RequestBody(
                    required = true,
                    description = "Datos de creación del alérgeno",
                    content = @Content(schema = @Schema(implementation = CreateAllergenDTO.class))
            )
            @Valid CreateAllergenDTO createAllergenDTO
    );

    @Operation(
            summary = "Editar un alérgeno",
            description = "Actualiza un alérgeno existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = AllergenDTO.class))
            ),
    })
    ResponseEntity<AllergenDTO> edit(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id,
            @RequestBody(
                    required = true,
                    description = "Datos a actualizar del alérgeno",
                    content = @Content(schema = @Schema(implementation = CreateAllergenDTO.class))
            )
            @Valid CreateAllergenDTO createAllergenDTO
    );

    @Operation(
            summary = "Desactiva un alérgeno",
            description = "Desactiva un alérgeno por su identificador. Responde 204 si se desactiva.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?> delete(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id
    );

    @Operation(
            summary = "Activa un alérgeno",
            description = "Activa un alérgeno desactivado. Devuelve el recurso activado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = AllergenDTO.class))
            ),
    })
    ResponseEntity<AllergenDTO>activate(
            @Parameter(description = "Identificador del alérgeno",example = "1")
            Long id
    );

    @Operation(
            summary = "Listado de alérgenos activos",
            description = "Devuelve todos los alérgenos activos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AllergenDTO.class)))
            ),
    })
    ResponseEntity<List<AllergenDTO>> findByActivateTrue();

    @Operation(
            summary = "Listado de alérgenos desactivados",
            description = "Devuelve todos los alérgenos desactivados. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AllergenDTO.class)))
            ),
    })
    ResponseEntity<List<AllergenDTO>> findByActivateFalse();
}
