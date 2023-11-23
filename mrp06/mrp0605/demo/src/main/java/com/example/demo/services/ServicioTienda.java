package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.domain.Producto;

public interface ServicioTienda {
    public ArrayList<Producto> findByCategory(Long categoria);
    public ArrayList<Producto> obtProductos();
    public void nuevoProducto(Producto producto);
    public String obtAllo();
    public void borrarProducto(Long id);
    public Producto obtenerPorId(Long id);
    public Producto editar(Producto producto);
}
