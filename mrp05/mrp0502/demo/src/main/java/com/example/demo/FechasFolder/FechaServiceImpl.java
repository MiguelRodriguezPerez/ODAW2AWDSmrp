package com.example.demo.FechasFolder;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class FechaServiceImpl implements FechasService{
    public int desdeUno(LocalDate ld){
        return ld.getDayOfYear()-(LocalDate.of(LocalDate.now().getYear(),1,1)).getDayOfYear();
    }
    public int entreDosFechas(LocalDate ld1, LocalDate ld2){
        return ld1.getDayOfYear()-ld2.getDayOfYear();
    }
    public String esBisiesto(LocalDate ld1){
        if(LocalDate.of(ld1.getYear(),12,31).getDayOfYear()==366) return "si";
        else return "no";
    }
    public ArrayList<Integer> esBisiestoEntreDos(Integer alloMayor, Integer alloMenor){
        System.out.println(alloMayor);
        System.out.println(alloMenor);
        ArrayList<Integer> resultado = new ArrayList<>();
        while(alloMenor<alloMayor){
            if(LocalDate.of(alloMenor,12,31).isLeapYear()) resultado.add(alloMenor);
            alloMenor++;
        }
        return resultado;
    }
}
