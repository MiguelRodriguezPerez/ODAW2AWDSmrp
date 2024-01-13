package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Cuenta;

public interface CuentaRepositorio extends JpaRepository<Cuenta,String>{
    @Query("select c.iban from Cuenta c")
    List<String>getAllIban();
}
