package com.example.demo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.EmpleadoController;
import com.example.demo.domain.Empleado;
import com.example.demo.domain.Genero;
import com.example.demo.services.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@TestInstance(Lifecycle.PER_CLASS)
public class EmpleadoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    EmpleadoController empleadoController;

    @MockBean
    EmpleadoService empleadoService;

    List<Empleado> listaEmpleados;
    Empleado eNull;

    @BeforeEach
    public void init(){
        listaEmpleados = new ArrayList<>();
        Empleado e1 = new Empleado(1l,"Jacinto","dos@outlook.es",500d,true,Genero.MASCULINO);
        Empleado e2 = new Empleado(2l,"Luis","tres@outlook.es",1500d,true,Genero.MASCULINO);
        Empleado e3 = new Empleado(3l,"Manuel","uno@outlook.es",2000d,false,Genero.MASCULINO);
        eNull = new Empleado(0l,"Iria","mar@gmail.com",1500d,true,Genero.FEMENINO);
        //Aunque le metas un dato mal, el servicio lo filtrará antes, como e2

        listaEmpleados.add(e1);listaEmpleados.add(e2);listaEmpleados.add(e3);
    }
    
    @Test
    public void testGetList() throws Exception{
        when(empleadoService.obtenerTodos()).thenReturn(listaEmpleados);
        mockMvc.perform(get("/empleado")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].nombre",is("Jacinto")));
    }

    @Test
    public void testFindById() throws Exception{
        when(empleadoService.obtenerPorId(2)).thenReturn(listaEmpleados.get(2));
        mockMvc.perform(get("/empleado/2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre",is("Manuel")));
    }

    @Test
    public void testPostEmpleado() throws Exception {
    when(empleadoService.añadir(eNull)).thenReturn(eNull);
    mockMvc.perform(post("/empleado") 
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(eNull)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.nombre", is("Iria")));
    }

    @Test
    public void testDeleteEmpleado() throws Exception {
        when (empleadoService.obtenerPorId(1)).thenReturn(listaEmpleados.get(0)); 
        doNothing().when(empleadoService).borrar(1L);
        mockMvc.perform(delete("/empleado/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
}
