package com.example.myshop.domain;

import java.time.LocalDate;
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
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @GeneratedValue
    @Id
    private Long id;
    private String nombre;
    private LocalDate fechaRegistro;

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private List<Valoracion> valoraciones;
}
