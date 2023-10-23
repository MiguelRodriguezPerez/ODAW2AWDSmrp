package com.example.demo.CalculosFolder;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public class CalculosServiceImpl implements CalculosService{

     public boolean obtPrimo(String num){
    /*@RequestParam requerirá un valor para ser introducido en la url con el nombre que se           
      indique en el paréntesis, String num es como se referirá el controlador internamente
      a ese valor y Model model es necesario para luego devolver los valores al html*/
        boolean esPrimo= true;//Necesario para saber si el número es primo
        Integer numero = 0;
        if (numero == 0|| numero == 1 || numero == 4) {
            esPrimo=false;//Si el numero es 0,1 o 4 no es primo
        }
        else if(esPrimo){
            for (int i = 2; i < numero / 2; i++) {
            if (numero % i == 0)esPrimo=false;
            // Si es divisible por cualquiera de estos números, no es primo
            }
        }
        return esPrimo;
      }

    public Double obtHipotenusa(Integer X, Integer Y){
        if(X<0||Y<0) return (double)-1;
        return Math.pow(X,2)+Math.pow(Y,2);
    }

    public TreeSet<Integer> obtCalculosSinRepetidos(Integer X){
        TreeSet<Integer> resultado = new TreeSet<>();
        if(X<1||X>100){
            resultado.add(-1); 
            return resultado;
        } 
        Random r = new Random();
        int adjudicar=0;
        for(int i = 0; i<X;i++){
            adjudicar=r.nextInt(100)+1;
            while(resultado.contains(adjudicar)){adjudicar=r.nextInt(100)+1;}
            //Evita que haya iteraciones en las que no se introduzcan números
            resultado.add(adjudicar);
      }
      return resultado;
    }

    public ArrayList<Integer> obtDivisores(int X){
      ArrayList<Integer> resultado = new ArrayList<>();
  
      for(int i = 1; i<X;i++){
        if(X%i==0)resultado.add(i);//Si el número i es divisor exacto de 0, se añadirá
        //al  resultado final
      }
      return resultado;
    }
}
