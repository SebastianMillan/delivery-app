package com.deliverysl.luxurydelivery.restaurant.controller;

import com.deliverysl.luxurydelivery.restaurant.dto.CreateRestaurandDTO;
import com.deliverysl.luxurydelivery.restaurant.dto.RestaurantDTO;
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

@Tag(name = "Restaurantes", description = "Operaciones CRUD sobre restaurantes")
public interface RestaurantControllerSwagger {

    @Operation(
            summary = "Listado de restaurantes",
            description = "Devuelve todos los restaurantes. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantDTO.class)))
            ),
    })
    ResponseEntity<List<RestaurantDTO>>findAll();

    @Operation(
            summary = "Detalle de un restaurante",
            description = "Recupera un restaurante por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Restaurante encontrado",
                    content = @Content(schema = @Schema(implementation = RestaurantDTO.class))
            )
    })
    ResponseEntity<RestaurantDTO> findById(
            @Parameter(description = "Identificador del alérgeno", example = "1")
            Long id
    );

    @Operation(
            summary = "Crear un restaurante",
            description = "Crea un nuevo restaurante. Devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = RestaurantDTO.class))
            ),
    })
    ResponseEntity<RestaurantDTO> create(
            @RequestBody(
            required = true,
            description = "Datos de creación del restaurante",
            content = @Content(schema = @Schema(implementation = CreateRestaurandDTO.class))
    )
            @Valid CreateRestaurandDTO createRestaurandDTO
    );

    @Operation(
            summary = "Editar un restaurante",
            description = "Actualiza un restaurante existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = RestaurantDTO.class))
            ),
    })
    ResponseEntity<RestaurantDTO>edit(
            @Parameter(description = "Identificador del restaurante", example = "1")
            Long id,
            @RequestBody(
                    required = true,
                    description = "Datos a actualizar del restaurante",
                    content = @Content(schema = @Schema(implementation = CreateRestaurandDTO.class))
            )
            @Valid CreateRestaurandDTO createRestaurandDTO
    );

    @Operation(
            summary = "Desactiva un restaurante",
            description = "Desactiva un restaurante por su identificador. Responde 204 si se desactiva.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?>delete(
            @Parameter(description = "Identificador del restaurante", example = "1")
            Long id
    );

    @Operation(
            summary = "Activa un restaurante",
            description = "Activa un restaurante desactivado. Devuelve el recurso activado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = RestaurantDTO.class))
            ),
    })
    ResponseEntity<RestaurantDTO>activate(
            @Parameter(description = "Identificador del restaurante", example = "1")
            Long id
    );

    @Operation(
            summary = "Listado de restaurantes activos",
            description = "Devuelve todos los restaurantes activos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantDTO.class)))
            ),
    })
    ResponseEntity<List<RestaurantDTO>> findByActivateTrue();

    @Operation(
            summary = "Listado de restaurantes desactivados",
            description = "Devuelve todos los restaurantes desactivados. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantDTO.class)))
            ),
    })
    ResponseEntity<List<RestaurantDTO>> findByActivateFalse();
}
