package com.example.demo.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.Cuenta;
import com.example.demo.domain.Movimiento;
import com.example.demo.domain.Dto.CuentaDto;
import com.example.demo.domain.Dto.MovimientoDto;
import com.example.demo.exceptions.CuentaNotFoundException;
import com.example.demo.exceptions.EmptyCuentasListException;
import com.example.demo.exceptions.EmptyMovimientosListException;
import com.example.demo.exceptions.SaldoNotZeroException;
import com.example.demo.exceptions.WrongAmountException;
import com.example.demo.services.CuentaService;
import com.example.demo.services.MovimientoService;

import jakarta.validation.Valid;
@RequestMapping("/cuenta")
@RestController
public class BancoController {
    
    @Autowired
    MovimientoService movimientoService;
    @Autowired
    CuentaService cuentaService;

    @GetMapping("/")
    public List<Cuenta> showCuentas(){
        List<Cuenta> lista;
        try{
            lista = cuentaService.obtenerCuentas();
        }
        catch(EmptyCuentasListException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return lista;
    }
    
    @GetMapping("/{iban}")
    public Cuenta showCuentaId(@PathVariable String iban){
        Cuenta cuenta;
        try{
            cuenta = cuentaService.obtenerPorIban(iban);
        }
        catch(CuentaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
        return cuenta;
    }

    @PostMapping("/guardar")
    public Cuenta showGuardarCuenta(@Valid @RequestBody CuentaDto cuentaDto){
        Cuenta cuenta = cuentaService.convertDtoToCuenta(cuentaDto);
        return cuentaService.guardarCuenta(cuenta);
    }

    @DeleteMapping("/borrar/{iban}")
    public ResponseEntity<?> showBorrarCuenta(@PathVariable String iban){
        try{
            Cuenta cuenta = cuentaService.obtenerPorIban(iban);
            cuentaService.borrarCuenta(iban);
        }
        catch(CuentaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
        catch(SaldoNotZeroException e2){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e2.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movimientos/{iban}")
    public List<Movimiento> showMovsPorCuenta(@PathVariable String iban){
        List<Movimiento> listaMovs;
        try{
            Cuenta cuenta = cuentaService.obtenerPorIban(iban);
            listaMovs = movimientoService.obtenerMovimientos(iban);
        }
        catch(CuentaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
        catch(EmptyMovimientosListException e2){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e2.getMessage());
        }
        return listaMovs;
    }

    @PostMapping("/nuevoMovimiento")
    public ResponseEntity<?> showGuardarMovimiento(@RequestBody MovimientoDto movimientoDto){
        try{
            Cuenta cuenta = cuentaService.obtenerPorIban(movimientoDto.getIban());
            Movimiento movimiento = movimientoService.convertirDtoToMovimiento(movimientoDto);
            movimientoService.hacerMovimiento(movimiento, movimientoDto.getIban());
        }
        catch(CuentaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
        catch(WrongAmountException e2){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e2.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
