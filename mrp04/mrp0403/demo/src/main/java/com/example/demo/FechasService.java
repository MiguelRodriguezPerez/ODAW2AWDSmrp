package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

public interface FechasService {
    public int desdeUno(LocalDate ld);
    public int entreDosFechas(LocalDate ld1, LocalDate ld2);
    public String esBisiesto(LocalDate ld1);
    public ArrayList<Integer> esBisiestoEntreDos(Integer alloUno, Integer alloDos);
}
