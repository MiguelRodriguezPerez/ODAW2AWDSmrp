package com.example.myshop.controllers;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myshop.domain.ContactFormInfo;

@Controller
public class MainController {

    @GetMapping({ "/", "/index", "/home" })
    public String showHome(@RequestParam() Optional<String> usuario,
            Model model) {

        int currentYear = LocalDate.now().getYear();
        model.addAttribute("year", currentYear);
        model.addAttribute("user", usuario.orElse(""));
        return "main/homeView";
    }

    @GetMapping("/contacta")
    public String formuContacta(Model model) {
        model.addAttribute("contactFormInfo", new ContactFormInfo());
        return "main/contactView";
    }

    @PostMapping("/contacta/submit")
    public String formuContactaSubmit(@Valid @ModelAttribute ContactFormInfo contactFormInfo,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors())
            return "redirect:/contacta";
        else
            return "main/contactProcessedView";
    }

    // @GetMapping("/productos")
    // public String showProductos(Model model) {
    // List<String> listaProductos = new ArrayList<>(Arrays.asList("Pantalla",
    // "Ordenador", "Rat"));
    // model.addAttribute("listaProd", listaProductos);
    // return "products";
    // }

    // ESTE MAPPING SE GESTIONA DESDE LA CLASSE MyConfig.java
    // @GetMapping("/quienes-somos")
    // public String showQuienesSomos() {
    // return "main/aboutUsView";
    // }
}
