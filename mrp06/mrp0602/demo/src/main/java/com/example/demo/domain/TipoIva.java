package com.example.demo.domain;

public enum TipoIva{
    GENERAL(21),
    REDUCIDO(10),
    SUPERREDUCIDO(4);

    private Integer tipo;

    TipoIva(Integer tipo){
        this.tipo=tipo;
    }

    public Integer getTipo() {
        return tipo;
    }
    
}