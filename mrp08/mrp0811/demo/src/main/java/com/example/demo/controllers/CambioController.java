package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.FormInfo;
import com.example.demo.services.CambioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CambioController {

    @Autowired
    CambioService cambioService;
    
    @GetMapping("/")
    public String showForm(@RequestParam(required = false) Integer err,Model model){
        if(err != null){
            if(err == 1){
                model.addAttribute("errorTxt", "Error parámetro entrada");
            }
            else if(err == 2){
                model.addAttribute("errorTxt", "Divisas iguales");
            }
        }
        model.addAttribute("formInfo", new FormInfo());
        return "indexView";
    }

    @PostMapping("/cambioMoneda")
    public String showFormSubmit(@Valid FormInfo formInfo, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) return "redirect:/?err=1";

        try{
            Double importeCambiado = cambioService.calcularImporteCambiado(formInfo.getMonedaOrigen(),
            formInfo.getMonedaDestino(),formInfo.getImporte());
            model.addAttribute("importeCambiado", importeCambiado);
            return "resultView";
        }
        catch(RuntimeException ex){
            return "redirect:/?err=2";
        }
    }
    
}
