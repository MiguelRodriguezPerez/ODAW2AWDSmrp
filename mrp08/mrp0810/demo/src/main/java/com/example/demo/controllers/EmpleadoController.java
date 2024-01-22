package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Empleado;

import com.example.demo.services.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @Operation(summary = "Devuelve todos los empleados",
            description = "Devuelve una lista con todos los empleados",
            tags = {"mrp0810", "get" })
        @ApiResponses({
            @ApiResponse(responseCode = "200",
                content = {@Content(schema = @Schema(implementation = Empleado.class),
                                        mediaType = "application/json") }),
        @ApiResponse(responseCode = "404",
                content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500",
                content = { @Content(schema = @Schema()) }) })

    @GetMapping("/empleados")
    public ResponseEntity<?> getAll() {
        List<Empleado> lista = empleadoService.obtenerTodos();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
        //Versión simplificada: ResponseEntity.ok(lista);
    }
    
    @Operation(summary = "Devuelve un empleado",
            description = "Devuelve un empleado mediante la obtención de su id",
            tags = {"mrp0810", "get" })
        @ApiResponses({
            @ApiResponse(responseCode = "200",
                content = {@Content(schema = @Schema(implementation = Empleado.class),
                                        mediaType = "application/json") }),
            @ApiResponse(responseCode = "404",
                content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500",
                content = { @Content(schema = @Schema()) }) })

    @GetMapping("/empleado/{id}")
    public ResponseEntity<?> showOne(@PathVariable Long id) {
        Empleado empleado = empleadoService.obtenerPorId(id);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado);
    }
    
    @Operation(summary = "Añade un empleado",
            description = "Crea un nuevo empleado",
            tags = {"mrp0810", "post" })
        @ApiResponses({
            @ApiResponse(responseCode = "200",
                content = {@Content(schema = @Schema(implementation = Empleado.class),
                                        mediaType = "application/json") }),
            @ApiResponse(responseCode = "404",
                content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500",
                    content = { @Content(schema = @Schema()) }) })
                    
    @PostMapping("/empleado")
    public ResponseEntity<?> showPost(@Valid @RequestBody Empleado nuevoEmpleado) {
        //Si no validos: ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
        Empleado empleadoAñadido = empleadoService.añadir(nuevoEmpleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoAñadido);
    }

    @Operation(summary = "Editar un empleado",
            description = "Permite editar un empleado mediante la obtención de su id",
            tags = {"mrp0810", "put" })
        @ApiResponses({
            @ApiResponse(responseCode = "200",
                content = {@Content(schema = @Schema(implementation = Empleado.class),
                                        mediaType = "application/json") }),
            @ApiResponse(responseCode = "404",
                content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500",
                content = { @Content(schema = @Schema()) }) })

    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> showEditSubmit(@Valid @RequestBody Empleado editEmpleado, @PathVariable Long id) {
        if (empleadoService.obtenerPorId(id) == null) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.notFound().build();
        }
        if (id != editEmpleado.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        empleadoService.editar(editEmpleado);
        return ResponseEntity.status(HttpStatus.OK).body(editEmpleado);
    }

    @Operation(summary = "Borrar un empleado",
            description = "Permite borrar un empleado mediante la obtención de su id",
            tags = {"mrp0810", "delete" })
        @ApiResponses({
            @ApiResponse(responseCode = "404",
                content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "204",
                content = { @Content(schema = @Schema()) }) })

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> showDelete(@PathVariable long id) {
        if (empleadoService.obtenerPorId(id) == null)
            return ResponseEntity.notFound().build();
        
        empleadoService.borrar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    // NO mezclar model con redirect.
}
