package com.example.demo;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculos")
/*@RequestMapping exige que a los distintos controladores les preceda
  el argumento de su paréntesis. Ejemplo: /calculos/primo */
public class NumController{
    @GetMapping("/primo")//Crea el mapeado para primo
    public String showPrimo(@RequestParam(name="numero",required = false) String num,Model model){
    /*@RequestParam requerirá un valor para ser introducido en la url con el nombre que se           
      indique en el paréntesis, String num es como se referirá el controlador internamente
      a ese valor y Model model es necesario para luego devolver los valores al html*/
        boolean esPrimo= true;//Necesario para saber si el número es primo
        Integer numero = 0;
        if(num==null) return "redirect:/errorNum";/*Si el usuario no introdujo valor en la url
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

        model.addAttribute("primo", esPrimo);
        model.addAttribute("valor", num);
        return "index";
      }
    @GetMapping("/hipotenusa/{X}/{Y}")//url que recibirá dos números X e Y
    public String showHipotenusa(@PathVariable Integer X,@PathVariable Integer Y, Model model){
        /*Debido al requerimiento del enunciado de mostrar en la url solo el valor de X
          e Y es obligatorio utilizar @PathVariable
          showHipotenusa recibe tres argumentos, la X que siempre recibirá el mismo nombre que 
          en la url si se usa @PathVariable, Y que funciona igual que X y model que
          añadirá los valores al html */
        if(X<0||Y<0) return "redirect:/errorNum";//Error al redirigir
        /*Si X o Y son números negativos, redirigirá al controlador del error*/
        model.addAttribute("resultado", Math.pow(X,2)+Math.pow(Y,2));
        /*Fórmula de la hipotenusa: es la suma de los cuadrados de X e Y 
          model.addAttribute() los envía al html*/
        return "index";
    }
    @GetMapping("/calculosSinRepetidos/{X}")
    public String showCalculosSinRepetidos(@PathVariable Integer X, Model model){
      //@PathVariable recibe una X como valor
      if(X<1||X>100) return "redirect:/errorNum";
      Random r = new Random();
      TreeSet<Integer> resultado = new TreeSet<>();
      int adjudicar=0;
      for(int i = 0; i<X;i++){
        adjudicar=r.nextInt(100)+1;
        while(resultado.contains(adjudicar)){adjudicar=r.nextInt(100)+1;}
        //Evita que haya iteraciones en las que no se introduzcan números
        resultado.add(adjudicar);
      }
      model.addAttribute("lista", resultado);    
      return "index";
    }
    @GetMapping("/divisores/{X}")
    public String showDivisores(@PathVariable int X,Model model){
      ArrayList<Integer> resultado = new ArrayList<>();
  
      for(int i = 1; i<X;i++){
        if(X%i==0)resultado.add(i);//Si el número i es divisor exacto de 0, se añadirá
        //al  resultado final
      }
      model.addAttribute("divisores",resultado);
      return "index";
    }
    @GetMapping("/divisoresDos")//Mismo mapping con @RequestParam
    public String showDivisoresDos(@RequestParam int X,Model model){
      ArrayList<Integer> resultado = new ArrayList<>();
  
      for(int i = 1; i<X;i++){
        if(X%i==0)resultado.add(i);//Si el número i es divisor exacto de 0, se añadirá
        //al  resultado final
      }
      model.addAttribute("divisoresDos",resultado);
      return "index";
    }
}
