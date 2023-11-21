package com.example.demo.domain;

import java.util.ArrayList;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "iban")
@ToString

public class Cuenta { 
    @NotEmpty
    private String iban;
    private String nombre;
    private Double saldo;
    private ArrayList<Movimiento> movimientos;

    public Cuenta(@NotEmpty String iban, String nombre, Double saldo) {
        this.iban = iban;
        this.nombre = nombre;
        this.saldo = saldo;
        this.movimientos = new ArrayList<>();
    }
}
