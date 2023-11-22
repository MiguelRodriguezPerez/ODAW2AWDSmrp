package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

public class Formulario {
    @Getter @Setter private String nombre;
    @Getter @Setter private String email;
    @Getter @Setter private String opcion;
    @Getter @Setter private String comentario;
    @Getter @Setter private boolean condiciones;
}
