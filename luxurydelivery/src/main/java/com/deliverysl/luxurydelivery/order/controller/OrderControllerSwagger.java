package com.deliverysl.luxurydelivery.order.controller;

import com.deliverysl.luxurydelivery.allergen.dto.AllergenDTO;
import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDto;
import com.deliverysl.luxurydelivery.order.dto.OrderDTO;
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

@Tag(name = "Pedidos", description = "Operaciones CRUD sobre pedidos")
public interface OrderControllerSwagger {

    @Operation(
            summary = "Listado de pedidos",
            description = "Devuelve todos los pedidos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<List<OrderDTO>> findAll();

    @Operation(
            summary = "Detalle de un pedido",
            description = "Recupera un pedido por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido encontrado",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))
            )
    })
    ResponseEntity<OrderDTO> findById(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long id
    );

    @Operation(
            summary = "Crear un pedido",
            description = "Crea un nuevo pedido. Devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))
            ),
    })
    ResponseEntity<OrderDTO> create(
            @RequestBody(
                required = true,
                description = "Datos de creación del pedido",
                content = @Content(schema = @Schema(implementation = CreateOrderDTO.class)))
            @Valid CreateOrderDTO createOrderDTO
    );

    @Operation(
            summary = "Editar un pedido",
            description = "Actualiza un pedido existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))
            ),
    })
    ResponseEntity<OrderDTO> edit(
            @RequestBody(
                    required = true,
                    description = "Datos a actualizar del pedido",
                    content = @Content(schema = @Schema(implementation = EditOrderDto.class))
            )
            @Valid EditOrderDto editOrderDto,
            @Parameter(description = "Identificador del pedido", example = "1")
            Long id
    );

    @Operation(
            summary = "Desactiva un pedido",
            description = "Desactiva un pedido por su identificador. Responde 204 si se desactiva.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?> delete(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long id
    );

    @Operation(
            summary = "Activa un pedido",
            description = "Activa un pedido desactivado. Devuelve el recurso activado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))
            ),
    })
    ResponseEntity<OrderDTO> activate(
            @Parameter(description = "Identificador del pedido",example = "1")
            Long id
    );

    @Operation(
            summary = "Listado de pedido activos",
            description = "Devuelve todos los pedido activos. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<List<OrderDTO>> findByActivateTrue();

    @Operation(
            summary = "Listado de pedido desactivados",
            description = "Devuelve todos los pedido desactivados. Si no hay resultados, responde 204.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<List<OrderDTO>> findByActivateFalse();
}

