package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Departamento {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE,
    mappedBy="departamento") // orphanRemoval = true
    /*FetchType.EAGER significa que la relación se efectuará de inmediato cuando se cargue 
    categoría, o en otras palabras, los empleados asociados a una categoría se cargarán con 
    dicha categoría

    cascade = CascadeType.REMOVE significa que si borras una categoría, también se borrarán
    los datos de los empleados de esa categoría

    mappedBy="categoria" significa que la clave principal es categoría, que es un atributo de 
    Empleado; en otras palabras es una clave ajena a Departamento

    orphanRemoval = true: Sirve para que cuando borres una entidad padre (Departamento) 
    también se borren sus hijos (Empleado)*/
    private List<Empleado> empleados = new ArrayList<>();

    public Departamento(String nombre){
        this.nombre = nombre;
    }
}
