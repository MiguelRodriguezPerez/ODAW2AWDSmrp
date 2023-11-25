package com.example.demo.services;

import java.util.ArrayList;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Empleo;

@Service
public class EmpleosService {
    
    ArrayList<Empleo> empleos = new ArrayList<>();

    public ArrayList<Empleo> obtEmpleos(){
        return empleos;
    }

    public void otroEmpleo(Empleo empleo){
        empleos.add(empleo);
    }

    public void borrarEmpleo(long id){
          Iterator<Empleo> comprobar = empleos.iterator();
          while(comprobar.hasNext()){
            Empleo empleo = comprobar.next();
            if(empleo.getId().equals(id)) comprobar.remove();
          }
    }

    public Empleo obtenerEmpleoPorId(long id){
        for(Empleo e: empleos){
            if(e.getId()==id) return e;
        }
        return null;
    }

    public void editarEmpleo(Empleo empleo){
        int posicion = empleos.indexOf(empleo);
        if(posicion != -1){
            empleos.set(posicion,empleo);
        }
        else{System.out.println("Maaaaaal");}
    }
}
