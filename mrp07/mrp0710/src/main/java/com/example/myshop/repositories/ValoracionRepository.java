package com.example.myshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshop.domain.Producto;
import com.example.myshop.domain.Usuario;
import com.example.myshop.domain.Valoracion;


public interface ValoracionRepository extends JpaRepository<Valoracion,Long>{
    List<Valoracion> findByProducto(Producto producto);
    List<Valoracion> findByUsuario(Usuario usuario);
}
