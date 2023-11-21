package com.example.demo.services;
import java.time.LocalDateTime;
//Pendiente mostrar movimientos y realizar movimientos
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Movimiento;

@Service
public class BancoServicio {
    
    ArrayList<Cuenta> cuentas = new ArrayList<>();

    public ArrayList<Cuenta> verCuentas(){
        return cuentas;
    }

    public ArrayList<Movimiento> obtenerMovimientos(String iban){
        for(Cuenta c: cuentas){
            if(c.getIban().equals(iban)) return c.getMovimientos();
        }
        return null;
    }
    
    public void nuevaCuenta(Cuenta cuenta){
        cuentas.add(new Cuenta(cuenta.getIban(),cuenta.getNombre(),cuenta.getSaldo()));
    }
    
    public Cuenta obtenerPorIban(String iban){
        for(Cuenta c: cuentas){
            if(c.getIban().equals(iban)) return c;
        }
        return null;
    }

    public void cambiarCuenta(Cuenta cuenta){
        int posicion = cuentas.indexOf(cuenta);
        System.out.println(cuenta.toString());
        for(Cuenta c: cuentas){
            if(cuenta.getIban().equals(c.getIban())){
                cuentas.set(posicion,new Cuenta(c.getIban(),cuenta.getNombre(),c.getSaldo()));
            } 
        }
    }

    public void borrarCuenta(String iban){
        Cuenta cuenta = this.obtenerPorIban(iban);
        if(cuenta!=null) cuentas.remove(cuenta);
    }

    public ArrayList<String> listaIbanes(){
        ArrayList<String> resultado = new ArrayList<>();
        for(Cuenta c: cuentas){
            resultado.add(c.getIban());
        }
        return resultado;
    }

    public void hacerMovimiento(String iban, Double importe){
        for(Cuenta c: cuentas){
            if(c.getIban().equals(iban)){
                ArrayList<Movimiento> operacion = c.getMovimientos();
                operacion.add(new Movimiento(c.getIban(),LocalDateTime.now(),importe));
                c.setSaldo(c.getSaldo() + importe);
            }
        }
    }
}
