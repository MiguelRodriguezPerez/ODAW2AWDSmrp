package com.example.demo.domain;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Proyecto {
    @Id
    @GeneratedValue
    private Long id;
    //resto de atributos, constructor sin id, ni empleadoProyecto
    private String nombre;
    @ToString.Exclude
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.REMOVE)
    private List<EmpleadoProyecto> empleadoProyecto = new ArrayList<>();
}