package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/errorNum")//url a la que redirigirá si se produce el error
    public String showError(){
        return "index";//Devuelve index como establece el enunciado
    }
}
