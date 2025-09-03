package com.deliverysl.luxurydelivery.orderline.controller;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.dto.OrderlineDTO;
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

@Tag(name = "Línea de pedido", description = "Operaciones CRUD sobre linea de pedidos de un pedido")
public interface OrderlineControllerSwagger {

    @Operation(
            summary = "Listado de linea de pedidos de un pedido",
            description = "Devuelve todas las lineas de pedidos de un pedido. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderlineDTO.class)))
            ),
    })
    ResponseEntity<List<OrderlineDTO>> findAll(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId
    );

    @Operation(
            summary = "Detalle de una linea de pedido de un producto",
            description = "Recupera una linea de pedido de un producto por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Linea de pedido encontrada",
                    content = @Content(schema = @Schema(implementation = OrderlineDTO.class))
            )
    })
    ResponseEntity<OrderlineDTO> findById(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId,
            @Parameter(description = "Identificador de la linea de pedido", example = "1")
            Long orderlineId
    );

    @Operation(
            summary = "Crear una linea de pedido en un pedido",
            description = "Crea una nueva linea de pedido de un pedido. Devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = OrderlineDTO.class))
            ),
    })
    ResponseEntity<OrderlineDTO> create(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId,
            @RequestBody(
                    required = true,
                    description = "Datos de creación de la linea de pedido",
                    content = @Content(schema = @Schema(implementation = CreateOrderlineDTO.class)))
            @Valid CreateOrderlineDTO createOrderlineDTO
    );

    @Operation(
            summary = "Editar una linea de pedido de un pedido",
            description = "Actualiza una linea de pedido existente en un pedido por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = OrderlineDTO.class))
            ),
    })
    ResponseEntity<OrderlineDTO> edit(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId,
            @Parameter(description = "Identificador de la linea de pedido", example = "1")
            Long orderlineId,
            @RequestBody(
                    required = true,
                    description = "Datos de edición de la linea de pedido",
                    content = @Content(schema = @Schema(implementation = CreateOrderlineDTO.class)))
            @Valid CreateOrderlineDTO createOrderlineDTO
    );

    @Operation(
            summary = "Desactiva una linea de pedido de un pedido",
            description = "Desactiva una linea de pedido de un pedido por su identificador. Responde 204 si se desactiva.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?> delete(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId,
            @Parameter(description = "Identificador de la linea de pedido", example = "1")
            Long orderlineId
    );

    @Operation(
            summary = "Activa una linea de pedido de un pedido",
            description = "Activa una linea de pedido desactivado de un pedido. Devuelve el recurso activado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = OrderlineDTO.class))
            ),
    })
    ResponseEntity<OrderlineDTO> activate(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId,
            @Parameter(description = "Identificador de la linea de pedido", example = "1")
            Long orderlineId
    );

    @Operation(
            summary = "Listado de lineas de pedidos activos de un pedido",
            description = "Devuelve todas las lineas de pedido activos de un pedido. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderlineDTO.class)))
            ),
    })
    ResponseEntity<List<OrderlineDTO>> findByActivateTrue(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId
    );

    @Operation(
            summary = "Listado de lineas de pedidos desactivados de un pedido",
            description = "Devuelve todas las lineas de pedidos desactivados de un pedido. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderlineDTO.class)))
            ),
    })
    ResponseEntity<List<OrderlineDTO>> findByActivateFalse(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long orderId
    );
}
