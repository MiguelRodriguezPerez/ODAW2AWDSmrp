package com.example.demo.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Data

public class Categoria {
    @Min(value = 0)
    private Long id;

    @NotEmpty
    private String nombre;  
}
