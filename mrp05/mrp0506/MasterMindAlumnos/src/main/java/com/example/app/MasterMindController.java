package com.example.app;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.model.FormInfo;
import com.example.app.model.FormInfo2;

@Controller
@Scope("session")//Realiza una nueva instancia por cada sesión web
public class MasterMindController {

    @Autowired//Llama al servicio con los métodos
    public MasterMindService masterMindService;

    @GetMapping("/")
    public String showHome(Model model) {//Mapeado de la página de inicio
        model.addAttribute("formInfo2", new FormInfo2());
        return "indexView";
    }

    @GetMapping("/juego")
    public String showGame(Model model) {//Mapeado del juego
        model.addAttribute("formInfo", new FormInfo());//Instancia un FormInfo, que se cubrirá en juegoView
        model.addAttribute("listaIntentos", masterMindService.masterMind.getListaIntentos());
        //Devuelve los intentos ya realizados
        model.addAttribute("estadoJuego", masterMindService.masterMind.getEstadoJuego());
        model.addAttribute("longitud", masterMindService.masterMind.getLongitud());
        model.addAttribute("restantes", masterMindService.masterMind.getIntentos()-masterMindService.masterMind.getListaIntentos().size()); 
        //Devuelve el resultado del intento
        return "juegoView";
    }

    @PostMapping("/juego")
    public String newTry(@Valid FormInfo formInfo, BindingResult bindingResult) {//Mapeado del juego tras una partida
        //FormInfo contiene los datos que se cubrieron en juegoView
        if(bindingResult.hasErrors()) return "errorView";
        if(formInfo.seRepite()) return "errorView";
        if(masterMindService.masterMind.contiene(Integer.parseInt(formInfo.getIntento()))) return "errorView";
        if(masterMindService.masterMind.getLongitud()!=formInfo.getIntento().length())return "errorView";
        masterMindService.comprobarIntento(formInfo.getIntento());//Devuelve el resultado de la partida
        return "redirect:/juego";

    }

    @PostMapping("/nuevoJuego")
    public String newGame(FormInfo2 formInfo2) {//Comienza una nueva partida
        masterMindService.nuevoJuego(formInfo2.getCadenaSize(),formInfo2.getNumIntentos());
        return "redirect:/juego";
    }
}
