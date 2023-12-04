package com.example.demo.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
public class Empleado{
  @Id
  @GeneratedValue
  @Min(value = 0)
  private Long id;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn (name = "CATEGORIA_ID")
  private Categoria categoria;

  @NotEmpty
  private String nombre;
  @Email(message = "Debe tener email valido")
  private String email;
  private Double salario;
  private boolean enActivo;
  private Genero genero;
  
  @ManyToOne
  @JoinColumn(name = "DEPTO_ID", foreignKey = @ForeignKey(name="DEPTO_ID_FK"))
  @ToString.Exclude
  @OnDelete (action = OnDeleteAction.CASCADE)
  private Departamento departamento;

  public Empleado(Categoria categoria, @NotEmpty String nombre,
      @Email(message = "Debe tener email valido") String email, Double salario, boolean enActivo, Genero genero,
      Departamento departamento) {
    this.categoria = categoria;
    this.nombre = nombre;
    this.email = email;
    this.salario = salario;
    this.enActivo = enActivo;
    this.genero = genero;
    this.departamento = departamento;
  }


  
}