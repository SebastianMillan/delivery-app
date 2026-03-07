package com.deliverysl.luxurydelivery.order.controller;

import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDTO;
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
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    /*@Operation(
            summary = "Activa o desactiva un pedido",
            description = "Activa o desactiva un pedido por su identificador. Responde 204 si se activa o desactiva.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?> deactive(
            @Parameter(description = "Identificador del pedido", example = "1")
            Long id
    );*/

    /*@Operation(
            summary = "Activa o desactiva un pedido",
            description = "Activa o desactiva un pedido. Devuelve el recurso activado o desactivado.",
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
    ResponseEntity<OrderDTO> toggle(
            @Parameter(description = "Identificador del pedido",example = "1")
            Long id
    );*/

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
    ResponseEntity<List<OrderDTO>> findAllByActivateTrue();

    @Operation(
            summary = "Cliente confirma el pedido",
            description = "El cliente confirma el pedido para que pase al siguiente estado",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cambio de estado confirmado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<OrderDTO> clientConfirmOrder(Long idOrder);

    @Operation(
            summary = "Cliente cancela el pedido",
            description = "El cliente cancela el pedido para que pase al siguiente estado",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cambio de estado cancelado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<OrderDTO> clientCancelOrder(Long idOrder);

    @Operation(
            summary = "Cliente reordena el pedido",
            description = "El cliente reordena el pedido para volver a abrir un pedido",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Pedido creado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<OrderDTO> clientReOrder(Long idOrder);

    @Operation(
            summary = "Jefe asigna empleado",
            description = "El Jefe asigna el empleado al pedido y cambia de estado",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Empleado asignado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<OrderDTO> bossConfirmOrder(Long idOrder,Long idEmployee);

    @Operation(
            summary = "empleado confirma que el pedido ya esta listo ",
            description = "empleado confirma que el pedido ya esta listo ",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido listo",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<OrderDTO> employeeStartReady(Long idOrder);

    @Operation(
            summary = "Rider se asigna al pedido ",
            description = "El rider se asigna al pedido ",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cambio de estado a on_delivery",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<OrderDTO> riderStartDelivery(Long idOrder,Long idRider);

    @Operation(
            summary = "Rider confirma la entrega del pedido ",
            description = "El rider confirma la entrega del pedido ",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cambio de estado a delivered",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
            ),
    })
    ResponseEntity<OrderDTO> riderDelivered(Long idOrder,Long idRider);
}

