package com.example.app.model;

import java.util.ArrayList;

public class MasterMind {
    
    private String numeroSecreto;//Número que tendrá que coincidir con el intento para que gane
    private ArrayList<Intento> listaIntentos;
    //Contiene un arraylist del objeto intentos, que son las veces que el jugador probará suerte
    private EstadoJuego estadoJuego;//Define si esta jugando, gana o pierde
    public int TAM_NUMERO;//4 números tendrá que adivinar
    public int MAX_INTENTOS;//Máximo intentos

    public MasterMind(int tamayo, int numIntentos) {
        TAM_NUMERO= tamayo;
        MAX_INTENTOS=numIntentos;
        listaIntentos = new ArrayList<>();
    }

    public String getNumeroSecreto() {//Devuelve el número secreto
        return numeroSecreto;
    }

    public void setNumeroSecreto(String numeroSecreto) {//Fija el número secreto
        this.numeroSecreto = numeroSecreto;
    }

    public ArrayList<Intento> getListaIntentos() {//Devuelve la lista de intentos
        return listaIntentos;
    }

    public void setListaIntentos(ArrayList<Intento> listaIntentos) {//Fija la lista de intentos
        this.listaIntentos = listaIntentos;
    }

    public EstadoJuego getEstadoJuego() {//Devuelve si gano, perdio o esta jugando
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {//Fija el estado de la partida
        this.estadoJuego = estadoJuego;
    }
    public int getLongitud(){
        return numeroSecreto.length();
    }
    public int getIntentos(){
        return MAX_INTENTOS;
    }
    public boolean contiene(int factor){
        for(Intento intento:listaIntentos){
            if(Integer.parseInt(intento.getCombinacion())==factor) return true;
        }
        return false;
    }
}
