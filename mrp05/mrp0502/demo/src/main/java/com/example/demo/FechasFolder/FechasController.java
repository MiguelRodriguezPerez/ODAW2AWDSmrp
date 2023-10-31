package com.example.demo.FechasFolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/fechas")
public class FechasController {
    @Autowired
    FechasService fechaService;
    @GetMapping("/formularioEntreDosFechas")
    public String showFormEntreDosFechas(Model model){
        model.addAttribute("fechas", new FechasForm());
        return "FormulariosFechas/fechasForm";
    }
    @PostMapping("/formularioEntreDosFechas/resultado")
    public String showSubmitEntreDosFechas(@Valid FechasForm fechasForm,BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return "FormulariosFechas/errorFechas";
        if(!fechasForm.getBisiesto()){
            model.addAttribute("resultadoF", fechaService.entreDosFechas(fechasForm.getFecha1(), fechasForm.getFecha2()));
        }
        else{

            model.addAttribute("resultadoFBisiesto", fechaService.esBisiestoEntreDos(fechasForm.getFecha1().getYear(), fechasForm.getFecha2().getYear()));
        }
        model.addAttribute("fechaUno", fechasForm.getFecha1());
        model.addAttribute("fechaDos", fechasForm.getFecha2());
        return "FormulariosFechas/fechasSubmit";
    }
}
