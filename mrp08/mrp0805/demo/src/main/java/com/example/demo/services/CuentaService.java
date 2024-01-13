package com.example.demo.services;

import java.util.List;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Dto.CuentaDto;

public interface CuentaService {
    List<Cuenta> obtenerCuentas();
    Cuenta guardarCuenta(Cuenta cuenta);
    Cuenta obtenerPorIban(String iban);
    Cuenta cambiarCuenta(String iban);
    void borrarCuenta(String iban);
    List<String> listaIbanes();
    Cuenta convertDtoToCuenta(CuentaDto cuentaDto);

}
