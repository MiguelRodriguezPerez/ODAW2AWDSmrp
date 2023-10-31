package com.example.demo.CalculosFolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculos")
public class NumerosController {
   @Autowired
   CalculosService calculosService;

    @GetMapping("/formulario")
    public String showFormulario(Model model){
        model.addAttribute("primo",new FormPrimo());
        model.addAttribute("hipotenusa",new FormHipotenusa());
        return "CalculosHtml/indexCalculos";
    }
    @PostMapping("/formulario/primoResultado")
    public String showSubmitPrimo(FormPrimo formPrimo,Model model){
        model.addAttribute("nPrimo", formPrimo.getNumero());
        model.addAttribute("rPrimo",calculosService.obtPrimo(Integer.toString(formPrimo.getNumero())));
        return "CalculosHtml/submitPrimo";
    }
    @PostMapping("/formulario/hipotenusaResultado")
    public String showSubmitHipotenusa(FormHipotenusa formHipotenusa,Model model){
        model.addAttribute("nh1", formHipotenusa.getCatetoUno());
        model.addAttribute("nh2", formHipotenusa.getCatetoDos());
        model.addAttribute("rHipotenusa", calculosService.obtHipotenusa(formHipotenusa.getCatetoUno(), formHipotenusa.getCatetoDos()));
        return "CalculosHtml/submitHipotenusa";
    }
    @PostMapping("/formulario/divisoresResultado")
    public String showSubmitDivisores(FormPrimo formPrimo,Model model){
        model.addAttribute("nd1", formPrimo.getNumero());
        model.addAttribute("rDivisores", calculosService.obtDivisores(formPrimo.getNumero()));
        return "CalculosHtml/submitDivisores";
    }
}
