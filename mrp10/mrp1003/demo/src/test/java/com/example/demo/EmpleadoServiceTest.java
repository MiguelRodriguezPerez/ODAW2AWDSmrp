package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.Genero;
import com.example.demo.repositories.EmpleadoRepository;
import com.example.demo.services.EmpleadoServiceImplBD;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class EmpleadoServiceTest {

    @InjectMocks
    EmpleadoServiceImplBD empleadoServiceImplBD;//Debería ser empeladoService

    @Mock
    EmpleadoRepository empleadoRepository;

    Empleado error1,error2,error3;
    List<Empleado> listaEmpleados;
    
    @BeforeEach
    public void init(){
        listaEmpleados = new ArrayList<>();
        error1 = new Empleado(1l,"Jacinto","dos@outlook.es",500d,true,Genero.MASCULINO);
        error2 = new Empleado(1l,"Luis","tres@outlook.es",5500d,true,Genero.MASCULINO);
        error3 = new Empleado(1l,"Manuel","uno@outlook.es",2000d,false,Genero.MASCULINO);
        //Aunque le metas un dato mal, el servicio lo filtrará antes

        listaEmpleados.add(error1);listaEmpleados.add(error2);listaEmpleados.add(error3);
    }
    
    @Test
    public void testAñadirFail(){
        Empleado e = new Empleado(4l,"Laura","nooo@outlook.es",15500d,true,Genero.FEMENINO);
        when(empleadoRepository.save(e)).thenReturn(e);
        assertNull(empleadoServiceImplBD.añadir(e));
        verify(empleadoRepository,times(0)).save(e);
    }

    @Test
    public void testAñadirSuccess(){
        Empleado e = new Empleado(4l,"Laura","nooo@outlook.es",1004d,true,Genero.FEMENINO);
        when(empleadoRepository.save(e)).thenReturn(e);
        Empleado e2 = empleadoServiceImplBD.añadir(e);
        assertEquals(e2.getNombre(), "Laura");
        verify(empleadoRepository,times(1)).save(e);
    }

    @Test
    public void testEditar(){
        assertNull(empleadoServiceImplBD.editar(error2));
    }

    @Test
    public void testObtenerTodos(){
        boolean resultado = true;
        when(empleadoRepository.findAll()).thenReturn(listaEmpleados);

        List<Empleado> lEmpleados = empleadoServiceImplBD.obtenerTodos();
        for (Empleado e: lEmpleados){
            if(!e.isEnActivo()) resultado = false;
        }

        assertTrue(resultado);
        verify(empleadoRepository,times(1)).findAll();
    }

    @Test
    public void testObtenerPorId(){
        when(empleadoRepository.findById(1l)).thenReturn(Optional.of(listaEmpleados.get(1)));
        assertEquals(1l,empleadoServiceImplBD.obtenerPorId(1l).getId());
        verify(empleadoRepository,times(1)).findById(1l);
    }
}
