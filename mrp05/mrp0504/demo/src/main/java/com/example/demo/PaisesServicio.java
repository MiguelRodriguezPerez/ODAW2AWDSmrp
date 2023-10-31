package com.example.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class PaisesServicio {
    private List<Pais> paises= new ArrayList<>();

    @Bean
    CommandLineRunner runner(){
        return args->{
            cargarPaisesDesdeFichero();
        };
    }
    public boolean cargarPaisesDesdeFichero(){
        List<String> lineas;
        String[] partes=new String[3];
        try{
            Path path = Paths.get("paises2019.csv");
            lineas = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
        }
        catch(IOException ex){
            System.err.printf("%nError:%s", ex.getMessage());
            return false;
        }
        for(String linea:lineas){
            partes = linea.split(";");
            if(partes.length==3)paises.add(new Pais(partes[0],partes[1],Long.parseLong(partes[2])));
            else return false;
        }
        return true;
    }

    public Pais getPais(String nombre){
        for(Pais pais:paises){
            if(pais.getNombrePais().equals(nombre))return pais;
        }
        return null;
    }

    public ArrayList<Pais> getPaises(){
        ArrayList<Pais> resultado = new ArrayList<>();
        for(Pais pais:paises){
            resultado.add(pais);
        }
        return resultado;
    }
    public ArrayList<String> getPaisesNombres(){
        ArrayList<String> resultado = new ArrayList<>();
        for(Pais pais:paises){
            resultado.add(pais.getNombrePais());
        }
        return resultado;
    }
}
