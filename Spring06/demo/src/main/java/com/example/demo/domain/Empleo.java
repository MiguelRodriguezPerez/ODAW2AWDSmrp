package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")

public class Empleo {
    private Long id;
    private String trabajo;
    private String sector;
    private Double sueldo;
    private Jornada jornada;
}
