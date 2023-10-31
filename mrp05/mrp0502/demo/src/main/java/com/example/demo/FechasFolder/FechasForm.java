package com.example.demo.FechasFolder;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class FechasForm {
    @NotNull @FutureOrPresent  private LocalDate fecha1;
    @NotNull @FutureOrPresent  private LocalDate fecha2;
    @NotNull private Boolean bisiesto;
}
