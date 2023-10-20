package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CalculosService calculosService;
    @GetMapping("/primo")//Crea el mapeado para primo
    public String showPrimo(@RequestParam(name="numero",required = false) String num,Model model){
        
        model.addAttribute("primo", calculosService.esPrimo(num));
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
        /*Si X o Y son números negativos, redirigirá al controlador del error*/
        model.addAttribute("resultado", calculosService.obtHipotenusa(X, Y));
        /*Fórmula de la hipotenusa: es la suma de los cuadrados de X e Y 
          model.addAttribute() los envía al html*/
        return "index";
    }
    @GetMapping("/calculosSinRepetidos/{X}")
    public String showCalculosSinRepetidos(@PathVariable Integer X, Model model){
      //@PathVariable recibe una X como valor
      
      model.addAttribute("lista", calculosService.obtListaSinRepetidos(X));    
      return "index";
    }
    @GetMapping("/divisores/{X}")
    public String showDivisores(@PathVariable int X,Model model){
      model.addAttribute("divisores", calculosService.verDivisores(X));
      return "index";
    }
    @GetMapping("/divisoresDos")//Mismo mapping con @RequestParam
    public String showDivisoresDos(@RequestParam int X,Model model){
      model.addAttribute("divisoresDos",calculosService.verDivisores(X));
      return "index";
    }
}
