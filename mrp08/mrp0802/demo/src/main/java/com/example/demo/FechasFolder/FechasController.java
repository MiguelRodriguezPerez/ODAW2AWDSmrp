package com.example.demo.FechasFolder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/fechas")
public class FechasController {
    @Autowired
    FechasService fechaService;

    @GetMapping("/{f1}")
    public ResponseEntity<?> show2B(@PathVariable LocalDate f1){
        HashMap<String,Integer> resultado = new HashMap<>();
        resultado.put("Dias desde el 1 de enero:",fechaService.desdeUno(f1));
        if(resultado.isEmpty())return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(resultado);
    }
    @GetMapping("/{f2}/{f3}")
    public ResponseEntity<?> show2C(@PathVariable LocalDate f2, @PathVariable LocalDate f3){
        HashMap<String,Integer> resultado = new HashMap<>();
        resultado.put("Dias entre las dos fechas", fechaService.entreDosFechas(f2, f3));
        if(resultado.isEmpty())return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(resultado);
    }
    @GetMapping("/")
    public ResponseEntity<?> show2D(){
        HashMap<String,Integer> resultado = new HashMap<>();
        resultado.put("Dias desde el uno de enero", fechaService.desdeUno(LocalDate.now()));
        if(resultado.isEmpty()) return ResponseEntity.notFound().build();   
        else return ResponseEntity.ok(resultado);
    }
    @GetMapping("/bisiesto/{f4}")
    public ResponseEntity<?> show2E(@PathVariable LocalDate f4){
        HashMap<String,String> resultado = new HashMap<>();
        resultado.put("Es bisiesto",fechaService.esBisiesto(f4));
        if(resultado.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(resultado);
    }
    @GetMapping("/bisiesto/{alloUno}/{alloDos}")
    public ResponseEntity<?> show2F(@PathVariable Integer alloUno,@PathVariable Integer alloDos){
        HashMap<String,ArrayList<Integer>> resultado = new HashMap<>();
        resultado.put("Allos bisiestos entre las dos fechas",fechaService.esBisiestoEntreDos(alloUno, alloDos));
        if(resultado.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(resultado);
    }
}
