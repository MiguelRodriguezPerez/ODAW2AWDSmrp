package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
@Service
public class CategoriaService {
    private ArrayList<Categoria> listaCategorias = new ArrayList<>();

    public ArrayList<Categoria> verCategorias(){
        return listaCategorias;
    }

    public void addCategoria(Categoria categoria){
        listaCategorias.add(categoria);
    }
    //No funciona
    public Categoria obtenerCategoriaPorId(Long id){
        for(Categoria c: listaCategorias){
            if(c.getId().equals(id)){
                System.out.println("Esto va bien");
                return c;
            }
            else{
                System.out.println("Categoria actual: " + c.getId());
                System.out.println("Evaluando id: " + id);
            }
        }
        return null;
    }

    public void editarCategoria(Categoria categoria){
        
        int posicion = listaCategorias.indexOf(categoria);//Devuelve -1
        listaCategorias.set(posicion,categoria);
    }

    public void borrarCategoria(Long id){
        Iterator<Categoria> iterator = listaCategorias.iterator();
        while(iterator.hasNext()){
            Categoria c = iterator.next();
            if(c.getId().equals(id)) iterator.remove();
        }
    }
}