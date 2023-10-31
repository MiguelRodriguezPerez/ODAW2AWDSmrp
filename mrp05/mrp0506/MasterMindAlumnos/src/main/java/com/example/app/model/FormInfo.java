package com.example.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Pattern;

public class FormInfo {//FormInfo es necesario para la partida en el html
    @Pattern(regexp = "^[0-9]*$", message = "Deben ser números")
    private String intento;//Contendrá el número del intento

    public String getIntento(){//Devuelve el número del intento
        return intento;
    }

    public void setIntento(String intento) {//Fija el número del intento
        this.intento = intento;
    }
    public boolean seRepite() {
        String comprobar="";

        for(int i=0;i<intento.length();i++){
            if(comprobar.indexOf(intento.charAt(i))!=-1)return true;
            comprobar+=intento.charAt(i);
            
        }
        return false;
    }
}
