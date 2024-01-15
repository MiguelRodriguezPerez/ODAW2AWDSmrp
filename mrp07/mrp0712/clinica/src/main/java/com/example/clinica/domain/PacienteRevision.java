package com.example.clinica.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.example.clinica.config.Tarifas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
// @DiscriminatorValue(value = "PacienteRevision")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class PacienteRevision extends Paciente {

    private LocalDate fechaUltimaRevision;

    public Double facturar(Tarifas tarifas) {
        Long edad = ChronoUnit.YEARS.between(this.getFechaNacimiento(), LocalDate.now());
        if (edad <= 65) {
            return tarifas.getTarifaRevisionAdulto();
        }
        return tarifas.getTarifaRevisionJubilado();
    }
    
}
