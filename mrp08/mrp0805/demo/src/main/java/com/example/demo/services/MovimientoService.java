package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Movimiento;
import com.example.demo.domain.Dto.MovimientoDto;

public interface MovimientoService {
    void hacerMovimiento(Movimiento movimiento, String iban);
    Movimiento convertirDtoToMovimiento(MovimientoDto movimientoDto);
    List<Movimiento> obtenerMovimientos(String iban);
}
