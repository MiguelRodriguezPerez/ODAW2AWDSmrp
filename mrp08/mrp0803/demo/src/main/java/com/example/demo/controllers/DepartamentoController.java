package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Departamento;
import com.example.demo.services.DepartamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    
    @Autowired
    DepartamentoService departamentoService;

    @GetMapping("/departamento")
    public ResponseEntity<?> getListDepartamentos(){
        List<Departamento> listaDepartamentos = departamentoService.obtenerTodos();
        if(listaDepartamentos.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(listaDepartamentos);
    }

    @GetMapping("/departamento/{id}")
    public ResponseEntity<?> getOneDepartamento(@PathVariable Long id){
        Departamento departamento = departamentoService.obtenerDepartamentoPorId(id);
        if(departamento == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(departamento);
    }

    @PostMapping("/departamento")
    public ResponseEntity<?> showDepartamentoNewSubmit(@Valid @RequestBody Departamento departamento){
        Departamento departamento2 = departamentoService.añadir(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(departamento2);
    }

    @PutMapping("/departamento/{id}")
    public ResponseEntity<?> editDepartamento(@Valid @RequestBody Departamento editDepartamento, @PathVariable Long id, Model model){
        Departamento departamento = departamentoService.obtenerDepartamentoPorId(id);
        if(departamento == null) return ResponseEntity.notFound().build();
        else{
            departamento = departamentoService.editarDepartamento(editDepartamento);
            return ResponseEntity.ok(departamento);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> deleteDepartamento(@PathVariable Long id){
        Departamento departamento = departamentoService.obtenerDepartamentoPorId(id);
        if(departamento == null) return ResponseEntity.notFound().build();
        departamentoService.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
