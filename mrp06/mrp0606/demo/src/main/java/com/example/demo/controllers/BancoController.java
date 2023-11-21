package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.IbanElegido;
import com.example.demo.services.BancoServicio;

import jakarta.validation.Valid;

@Controller
public class BancoController {
    
    @Autowired
    BancoServicio bancoServicio;
    
    @GetMapping({"/","/index","/inicio"})
    public String showIndex(Model model){  
        model.addAttribute("cuentas", bancoServicio.verCuentas());
        return "index";

    }

    @GetMapping("/nuevaCuenta")
    public String showNuevaCuenta(Model model){
        model.addAttribute("cuenta", new Cuenta());
        return "crearCuenta";
    }

    @PostMapping("/nuevaCuenta/submit")
    public String showSubmitNuevaCuenta(@Valid Cuenta cuenta, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "error";
        bancoServicio.nuevaCuenta(cuenta);
        return "redirect:/";
    }

    @GetMapping("/editar/{iban}")
    public String showEditar(@PathVariable String iban, Model model){
        Cuenta cuenta = bancoServicio.obtenerPorIban(iban);
        if(cuenta!=null){
            model.addAttribute("cuenta", cuenta);
            return "editarCuenta";
        }
        return "redirect:/";
    }

    @PostMapping("/editarCuenta/submit")
    public String showEditarSubmit(Cuenta cuenta){
        bancoServicio.cambiarCuenta(cuenta);
        return "redirect:/";
    }

    @GetMapping("/borrar/{iban}")
    public String showBorrar(@PathVariable String iban){
        bancoServicio.borrarCuenta(iban);
        return "redirect:/";
    }
    @GetMapping("/movimientos/{usuario}")
    public String showMovimientos(@PathVariable String usuario, Model model){
        model.addAttribute("movimientos", bancoServicio.obtenerMovimientos(usuario));
        return "verMovimientos";
    }

    @GetMapping("/nuevoMovimiento")
    public String showIbanDisponibles(Model model){
        model.addAttribute("listaIbanes", bancoServicio.listaIbanes());
        model.addAttribute("ibanElegido", new IbanElegido());
        return "elegirIban";
    }

    @PostMapping("/nuevoMovimiento/submit")
    public String showMovimiento(@Valid IbanElegido ibanElegido, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "errorImporte";
        bancoServicio.hacerMovimiento(ibanElegido.getIban(), ibanElegido.getImporte());
        return "redirect:/";
    }
}
