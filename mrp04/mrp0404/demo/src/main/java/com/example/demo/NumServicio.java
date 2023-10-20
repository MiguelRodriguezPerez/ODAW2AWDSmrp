package com.example.demo;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class NumServicio {
    Random random = new Random();
    public Set<Integer> lista = new LinkedHashSet<>();

    public Set<Integer> obtLista(){
        return lista;
    }

    public void nuevoNumero(){
        boolean alladido;
        do{alladido = lista.add(random.nextInt(100)+1);}
        while(!alladido);
    }

    public void borrarNumero(Integer numero){
        lista.remove(numero);
    }
}
