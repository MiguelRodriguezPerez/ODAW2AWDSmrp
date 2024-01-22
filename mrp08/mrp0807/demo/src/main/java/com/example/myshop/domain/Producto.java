package com.example.myshop.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
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
public class Producto {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    private boolean enOferta;

    private TipoIva tipoIva; // enumeración

    private Double precio;

    @ManyToOne
    private Categoria categoria;

    @ToString.Exclude
    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    private List<Valoracion> valoraciones;

}
