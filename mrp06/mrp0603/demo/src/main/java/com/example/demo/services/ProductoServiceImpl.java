package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Producto;

@Service
public class ProductoServiceImpl implements ProductoService{
    
    private ArrayList<Producto> productos = new ArrayList<>();

    public ArrayList<Producto> obtProductos(){
        return productos;
    }

    public void nuevoProducto(Producto producto){
        productos.add(producto);
    }


    public String obtAllo(){
        return Integer.toString(LocalDate.now().getYear());
    }

    public void borrarProducto(Long id){
        Iterator<Producto> comprobar = productos.iterator();
        while(comprobar.hasNext()){
            Producto producto = comprobar.next();
            if(producto.getId()==id)comprobar.remove();
        }
    }

    public Producto obtenerPorId(Long id){
        for(Producto p: productos){
            if(p.getId()==id) return p;
        }
        return null;
    }

    public Producto editar(Producto producto){
        int posicion = productos.indexOf(producto);
        System.out.println(posicion);
        if(posicion==-1) return null;
        productos.set(posicion,producto);
        return producto;
    }
}
