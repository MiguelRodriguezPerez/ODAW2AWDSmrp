package com.example.demo;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

@Service
public class CalculosService{
    public boolean esPrimo(String num){
        boolean esPrimo= true;//Necesario para saber si el número es primo
        Integer numero = 0;
        if(num==null) return false;/*Si el usuario no introdujo valor en la url
        redirige al controlador del error*/
        try{numero = Integer.parseInt(num);}//Si el numero es texto, controlará el error
        catch(NumberFormatException e){num="[Error al introducir numero]";}
        
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
        if(X<0||Y<0) return (double) -1;
        return Math.pow(X,2)+Math.pow(Y,2);
    }
    public TreeSet<Integer> obtListaSinRepetidos(Integer X){
        if(X<1||X>100) return null;
        Random r = new Random();
        TreeSet<Integer> resultado = new TreeSet<>();
        int adjudicar=0;
        for(int i = 0; i<X;i++){
        adjudicar=r.nextInt(100)+1;
        while(resultado.contains(adjudicar)){adjudicar=r.nextInt(100)+1;}
        //Evita que haya iteraciones en las que no se introduzcan números
        resultado.add(adjudicar);
      }
      return resultado;
    }
    public ArrayList<Integer> verDivisores(int X){
        ArrayList<Integer> resultado = new ArrayList<>();

        for(int i = 1; i<X;i++){
            if(X%i==0)resultado.add(i);//Si el número i es divisor exacto de 0, se añadirá
            //al  resultado final
        }
        return resultado;
    }
}