package com.example.demo.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="iban")
@Data

public class IbanElegido {
    private String iban;
    @Min(value=-300)
    @Max(value=1000)
    private Double importe;    
}
