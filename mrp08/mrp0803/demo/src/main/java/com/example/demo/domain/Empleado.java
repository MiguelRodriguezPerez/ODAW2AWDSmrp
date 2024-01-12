package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

  @NotEmpty
  private String nombre;
  @Email(message = "Debe tener email valido")
  private String email;
  private Double salario;
  private boolean enActivo;
  private Genero genero;
  @ManyToOne
  private Departamento departamento;
}