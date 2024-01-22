package com.example.myshop.services;

import java.util.List;

import com.example.myshop.domain.Producto;
import com.example.myshop.domain.Usuario;
import com.example.myshop.domain.Valoracion;

public interface ValoracionService {
    Valoracion guardar(Valoracion valoracion);
    List<Valoracion> obtenerValoracionesPorProducto(Producto producto);
    List<Valoracion> obtenerValoracionesPorUsuario(Usuario usuario);
    Valoracion obtenerPorId(Long id);
    Valoracion editar(Valoracion valoracion);
    void borrar(Long id);
}
