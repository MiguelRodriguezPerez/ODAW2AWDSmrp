package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class WorklineService {
    public String obtAllo(){
        return Integer.toString(LocalDate.now().getYear());
    }
    public ArrayList<String> displayEmpresas(){
        ArrayList<String> empresas = new ArrayList<>();
        empresas.add("Zara");
        empresas.add("Indra");
        empresas.add("Ford");
        empresas.add("Carrefour");
        empresas.add("Ikea");
        return empresas;
    }
    public int obtMes(){
        return LocalDate.now().getMonthValue();
    }
}
