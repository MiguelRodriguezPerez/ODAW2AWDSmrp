package com.example.clinica.domain;

import com.example.clinica.config.Tarifas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
// @DiscriminatorValue(value = "PacienteConsulta")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class PacienteConsulta extends Paciente {

    private String motivoConsulta;

    public Double facturar(Tarifas tarifas) {
        return tarifas.getTarifaConsulta();
    }
    
}
