package com.example.demo.services;
//Pendiente mostrar movimientos y realizar movimientos
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Dto.CuentaDto;
import com.example.demo.exceptions.CuentaNotFoundException;
import com.example.demo.exceptions.EmptyCuentasListException;
import com.example.demo.exceptions.SaldoNotZeroException;
import com.example.demo.repositories.CuentaRepositorio;
import com.example.demo.repositories.MovimientoRepositorio;

@Service
public class CuentaServiceImplBD implements CuentaService{
    
    @Autowired
    CuentaRepositorio cuentaRepositorio;
    @Autowired
    MovimientoRepositorio movimientoRepositorio;

    public List<Cuenta> obtenerCuentas(){
        List<Cuenta> lista = cuentaRepositorio.findAll();
        if(lista.isEmpty()) throw new EmptyCuentasListException();
        return lista;
    }

    
    public Cuenta guardarCuenta(Cuenta cuenta){
        return cuentaRepositorio.save(cuenta);
    }
    
    public Cuenta obtenerPorIban(String iban){
        return cuentaRepositorio.findById(iban).orElseThrow(()-> new CuentaNotFoundException(iban));
    }

    public Cuenta cambiarCuenta(String iban){
        return cuentaRepositorio.findById(iban).get();
    }

    public void borrarCuenta(String iban){
        Cuenta cuenta = obtenerPorIban(iban);
        if(cuenta.getSaldo() != 0) throw new SaldoNotZeroException(iban);
        cuentaRepositorio.delete(cuenta);
    }

    public List<String> listaIbanes(){
        return cuentaRepositorio.getAllIban();
    }

    @Override
    public Cuenta convertDtoToCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta(cuentaDto.getIban(),cuentaDto.getAlias(),0d,null);
        return cuenta;
    }
}
