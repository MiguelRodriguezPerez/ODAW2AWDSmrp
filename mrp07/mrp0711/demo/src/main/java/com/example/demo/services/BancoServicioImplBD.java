package com.example.demo.services;
import java.time.LocalDateTime;
//Pendiente mostrar movimientos y realizar movimientos
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Movimiento;
import com.example.demo.repositories.CuentaRepositorio;
import com.example.demo.repositories.MovimientoRepositorio;

@Service
public class BancoServicioImplBD implements BancoServicio{
    
    @Autowired
    CuentaRepositorio cuentaRepositorio;
    @Autowired
    MovimientoRepositorio movimientoRepositorio;

    public List<Cuenta> verCuentas(){
        return cuentaRepositorio.findAll();
    }

    public List<Movimiento> obtenerMovimientos(String iban){
        return movimientoRepositorio.findByCuenta(cuentaRepositorio.findById(iban).orElse(null));
    }
    
    public Cuenta nuevaCuenta(Cuenta cuenta){
        return cuentaRepositorio.save(cuenta);
    }
    
    public Cuenta obtenerPorIban(String iban){
        return cuentaRepositorio.findById(iban).get();
    }

    public Cuenta cambiarCuenta(String iban){
        return cuentaRepositorio.findById(iban).get();
    }

    public void borrarCuenta(String iban){
        cuentaRepositorio.delete(cuentaRepositorio.findById(iban).get());
    }

    public List<String> listaIbanes(){
        return cuentaRepositorio.getAllIban();
    }

    public void hacerMovimiento(String iban, Double importe){
        movimientoRepositorio.save(new Movimiento(null,cuentaRepositorio.findById(iban).orElse(null),LocalDateTime.now(),importe));
        Cuenta cuenta = cuentaRepositorio.findById(iban).orElse(null);
        cuenta.setSaldo(cuenta.getSaldo() + importe);
        cuentaRepositorio.save(cuenta);
    }
}
