package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Departamento;
import com.example.demo.domain.Empleado;
import com.example.demo.domain.Genero;
import com.example.demo.services.DepartamentoService;
import com.example.demo.services.EmpleadoService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(EmpleadoService empleadoService, DepartamentoService departamentoService) {
		return args -> {
			departamentoService.añadir(new Departamento("Direccion"));
			departamentoService.añadir(new Departamento("RR.HH"));
			
			empleadoService.añadir(new Empleado(0L,"pepe", "pepe@gmail.com",
			1000d, true, Genero.MASCULINO,departamentoService.obtenerDepartamentoPorId(1l)));
			empleadoService.añadir(new Empleado(0L, "ana", "ana@gmail.com",
			2000d, true,Genero.FEMENINO,departamentoService.obtenerDepartamentoPorId(2l)));
		};
	}
}
