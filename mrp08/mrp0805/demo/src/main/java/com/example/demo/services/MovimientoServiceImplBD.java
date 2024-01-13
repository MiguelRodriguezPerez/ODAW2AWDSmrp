package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Movimiento;
import com.example.demo.domain.Dto.MovimientoDto;
import com.example.demo.exceptions.EmptyMovimientosListException;
import com.example.demo.exceptions.WrongAmountException;
import com.example.demo.repositories.CuentaRepositorio;
import com.example.demo.repositories.MovimientoRepositorio;
@Service
public class MovimientoServiceImplBD implements MovimientoService{

    @Autowired
    CuentaRepositorio cuentaRepositorio;
    @Autowired
    MovimientoRepositorio movimientoRepositorio;
    @Autowired
    CuentaService cuentaService;

    public void hacerMovimiento(Movimiento movimiento,String iban){
        if(movimiento.getImporte()>1000 || movimiento.getImporte()<-300) throw new WrongAmountException(movimiento.getImporte());
        movimientoRepositorio.save(movimiento);
        Cuenta cuenta = cuentaRepositorio.findById(iban).orElse(null);
        cuenta.setSaldo(cuenta.getSaldo() + movimiento.getImporte());
        cuentaRepositorio.save(cuenta);
    }

    @Override
    public Movimiento convertirDtoToMovimiento(MovimientoDto movimientoDto) {
        Movimiento movimiento = new Movimiento(0l,cuentaService.obtenerPorIban(movimientoDto.getIban()),
        LocalDateTime.now(),movimientoDto.getImporte());
        return movimiento;
    }

    public List<Movimiento> obtenerMovimientos(String iban){
        List<Movimiento> lista = movimientoRepositorio.findByCuenta(cuentaRepositorio.findById(iban).orElse(null));
        if(lista.isEmpty()) throw new EmptyMovimientosListException(iban);
        else return lista;
    }
}
