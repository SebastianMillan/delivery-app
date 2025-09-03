package com.deliverysl.luxurydelivery.user.controller;

import com.deliverysl.luxurydelivery.user.dto.CreateEmployeeDTO;
import com.deliverysl.luxurydelivery.user.dto.EmployeeDTO;
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

@Tag(name = "Empleados", description = "Operaciones CRUD sobre empleados")
public interface EmployeeControllerSwagger {

    @Operation(
            summary = "Crear un empleado",
            description = "Crea un nuevo empleado y devuelve el recurso creado.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Creado",
                    headers = @Header(name = "Location", description = "URI del recurso creado"),
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))
            )
    })
    ResponseEntity<EmployeeDTO>create(
            @RequestBody(
            required = true,
            description = "Datos de creación del empleado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CreateEmployeeDTO.class))
    )
            @Valid CreateEmployeeDTO createEmployeeDTO
    );

    @Operation(
            summary = "Editar un empleado",
            description = "Actualiza un empleado existente por su identificador.",
            security = { @SecurityRequirement(name = "bearerAuth") }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class))
            )
    })
    ResponseEntity<EmployeeDTO>edit(
            @RequestBody(
                    required = true,
                    description = "Datos de edición del empleado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateEmployeeDTO.class))
            )
            @Valid CreateEmployeeDTO createEmployeeDTO,
            @Parameter(description = "Identificador del empleado", example = "1")
            Long id
    );
}
