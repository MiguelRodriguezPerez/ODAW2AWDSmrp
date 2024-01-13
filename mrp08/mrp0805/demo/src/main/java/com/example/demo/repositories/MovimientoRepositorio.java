package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Movimiento;

public interface MovimientoRepositorio extends JpaRepository<Movimiento,Long>{
    List<Movimiento>findByCuenta(Cuenta cuenta);
}
