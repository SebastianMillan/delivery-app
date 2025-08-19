package com.deliverysl.luxurydelivery.product.controller;

import com.deliverysl.luxurydelivery.product.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@Tag(name = "Productos", description = "Operaciones de consulta y eliminación de productos")
public interface ProductControllerSwagger {

    @Operation(
            summary = "Listado de productos",
            description = "Devuelve todos los productos. Si no hay resultados, responde 204 (sin contenido).",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado recuperado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
                    )
            )
    })
    ResponseEntity<List<ProductDTO>> findAll();

    @Operation(
            summary = "Detalle de un producto",
            description = "Recupera un producto por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            )
    })
    ResponseEntity<ProductDTO> findById(
            @Parameter(description = "Identificador del producto", example = "1")
            Long id
    );

    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina un producto por su identificador. Devuelve 204 si se elimina correctamente.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    ResponseEntity<?> delete(
            @Parameter(description = "Identificador del producto", example = "1")
            Long id
    );
}
