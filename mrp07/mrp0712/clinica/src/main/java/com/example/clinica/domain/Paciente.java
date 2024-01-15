package com.example.clinica.domain;

import java.time.LocalDate;

import com.example.clinica.config.Tarifas;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name = "tipoPaciente")
@Data
@EqualsAndHashCode(of="dni")
@NoArgsConstructor
@AllArgsConstructor
public abstract class Paciente {
    @Id
    @GeneratedValue
    private Long id;

    private String dni;
    private String nombre;
    private LocalDate fechaNacimiento;

    public abstract Double facturar(Tarifas tarifas);
}
