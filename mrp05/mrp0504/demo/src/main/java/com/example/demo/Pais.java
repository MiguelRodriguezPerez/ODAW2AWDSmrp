package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class Pais{
    private String nombrePais;
    private String capital;
    private long poblacion;

    public Pais(String nombrePais, String capital, long poblacion) {
        this.nombrePais = nombrePais;
        this.capital = capital;
        this.poblacion = poblacion;
    }
    public Pais(){
        
    }
    @Override
    public String toString() {
        return "Pais [nombre=" + nombrePais + ", capital=" + capital + ", poblacion=" + poblacion + "]";
    }
    
}