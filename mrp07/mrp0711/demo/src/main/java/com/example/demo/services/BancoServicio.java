package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Movimiento;

public interface BancoServicio {
    List<Cuenta> verCuentas();
    List<Movimiento> obtenerMovimientos(String iban);
    Cuenta nuevaCuenta(Cuenta cuenta);
    Cuenta obtenerPorIban(String iban);
    Cuenta cambiarCuenta(String iban);
    void borrarCuenta(String iban);
    List<String> listaIbanes();
    void hacerMovimiento(String iban, Double importe);
}
