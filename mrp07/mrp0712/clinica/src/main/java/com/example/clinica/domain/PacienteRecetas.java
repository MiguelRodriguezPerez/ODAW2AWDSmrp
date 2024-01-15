package com.example.clinica.domain;

import com.example.clinica.config.Tarifas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
// @DiscriminatorValue(value = "PacienteRecetas")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRecetas extends Paciente {

    private Integer listaMedicamentos;

    public Double facturar(Tarifas tarifas) {
        return tarifas.getTarifaReceta() * listaMedicamentos;
    }
    
}
