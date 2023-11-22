package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of="id")
public class Producto {

    private Long id;
    private String nombre;
    private Boolean enOferta;
    private TipoIva tipoIva;
    private Double precio;

}
