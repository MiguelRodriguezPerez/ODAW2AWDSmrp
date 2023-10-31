package com.example.app.model;

public class Intento {//Clase en la que se desarrolla el juego

    private String combinacion;//Contendrá los números ganadores
    private int bienColocados;//Contendrá la cantidad de los números que coincidan
    private int malColocados;//Contendrá la cantidad de los números que no coincidan

    public Intento(String combinacion, int bienColocados, int malColocados) {
        this.combinacion = combinacion;
        this.bienColocados = bienColocados;
        this.malColocados = malColocados;
    }

    public String getCombinacion() {//Devuelve los números ganadores
        return combinacion;
    }

    public void setCombinacion(String combinacion) {//Fija los números ganadores
        this.combinacion = combinacion;
    }

    public int getBienColocados() {//Devuelve los números que coincidan
        return bienColocados;
    }

    public void setBienColocados(int bienColocados) {//Fija los números que coincidan
        this.bienColocados = bienColocados;
    }

    public int getMalColocados() {//Devuelve los números que no coincidan
        return malColocados;
    }

    public void setMalColocados(int malColocados) {//Fija los números que no coincidan
        this.malColocados = malColocados;
    }

}
