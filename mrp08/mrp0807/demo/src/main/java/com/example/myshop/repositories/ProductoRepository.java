package com.example.myshop.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshop.domain.Categoria;
import com.example.myshop.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long>{
    public List<Producto> findByCategoria(Categoria idCategoria);
}
