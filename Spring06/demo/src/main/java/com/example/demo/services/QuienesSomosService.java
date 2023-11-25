package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class QuienesSomosService {
    ArrayList<String> empresas = new ArrayList<>();

    public String obtAllo(){
        return Integer.toString(LocalDate.now().getYear());
    }
    public ArrayList<String> displayEmpresas(){
        return empresas;
    }
    public int obtMes(){
        return LocalDate.now().getMonthValue();
    }

    public void otraEmpresa(String empresa){
        empresas.add(empresa);
    }
}
