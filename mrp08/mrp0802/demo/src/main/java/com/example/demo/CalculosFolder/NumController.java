package com.example.demo.CalculosFolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculos")
/*@RequestMapping exige que a los distintos controladores les preceda
  el argumento de su paréntesis. Ejemplo: /calculos/primo */
public class NumController{
    @Autowired
    CalculosService calculosService;
    @GetMapping("/primo")//Crea el mapeado para primo
    public ResponseEntity<?> getPrimo(@RequestParam String num){
        boolean esPrimo= true;//Necesario para saber si el número es primo
        Integer numero = Integer.parseInt(num);
        if (numero == 0|| numero == 1 || numero == 4) {
            esPrimo=false;//Si el numero es 0,1 o 4 no es primo
        }
        else if(esPrimo){
            for (int i = 2; i < numero / 2; i++) {
            if (numero % i == 0)esPrimo=false;
            // Si es divisible por cualquiera de estos números, no es primo
            }
        }
        HashMap<Integer,Boolean> resultado = new HashMap<Integer,Boolean>();
        resultado.put(numero,esPrimo);
        if(resultado.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(resultado);
      }
    @GetMapping("/hipotenusa/{X}/{Y}")//url que recibirá dos números X e Y
    public ResponseEntity<?> showHipotenusa(@PathVariable Integer X,@PathVariable Integer Y){
        HashMap<String,Double> resultado = new HashMap<>();
        resultado.put("Hipotenusa",Math.pow(X,2)+Math.pow(Y,2));
        if((X<0||Y<0) || resultado.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(resultado);
    }
    @GetMapping("/calculosSinRepetidos/{X}")
    public ResponseEntity<?> showCalculosSinRepetidos(@PathVariable Integer X){
      Random r = new Random();
      TreeSet<Integer> resultado = new TreeSet<>();
      int adjudicar=0;
      for(int i = 0; i<X;i++){
        adjudicar=r.nextInt(100)+1;
        while(resultado.contains(adjudicar)){adjudicar=r.nextInt(100)+1;}
        //Evita que haya iteraciones en las que no se introduzcan números
        resultado.add(adjudicar);
      }
      HashMap<String,TreeSet<Integer>> resultadoFinal = new HashMap<>();  
      resultadoFinal.put("Calculos sin Repetidos",resultado);
      if((X<1||X>100) || resultadoFinal.isEmpty()) return ResponseEntity.notFound().build();
      else return ResponseEntity.ok(resultadoFinal);
    }

    @GetMapping("/divisores/{X}")
    public ResponseEntity<?> showDivisores(@PathVariable int X){
      ArrayList<Integer> resultado = new ArrayList<>();
  
      for(int i = 1; i<X;i++){
        if(X%i==0)resultado.add(i);//Si el número i es divisor exacto de 0, se añadirá
        //al  resultado final
      }
      HashMap<String,ArrayList<Integer>> fin = new HashMap<>();
      fin.put("Divisores",resultado);

      if(fin.isEmpty()) return ResponseEntity.notFound().build();
      else return ResponseEntity.ok(fin);
    }
    @GetMapping("/divisoresDos")//Mismo mapping con @RequestParam
    public ResponseEntity<?> showDivisoresDos(@RequestParam int X){
      ArrayList<Integer> resultado = new ArrayList<>();
  
      for(int i = 1; i<X;i++){
        if(X%i==0)resultado.add(i);//Si el número i es divisor exacto de 0, se añadirá
        //al  resultado final
      }
      HashMap<String,ArrayList<Integer>> fin = new HashMap<>();
      fin.put("Divisores Dos", resultado);

      if(fin.isEmpty()) return ResponseEntity.notFound().build();
      else return ResponseEntity.ok(fin);
    }
}
