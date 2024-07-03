package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Empleado;
import com.example.demo.domain.Genero;
import com.example.demo.services.EmpleadoService;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(EmpleadoService empleadoService) {
		return args -> {
			// empleadoService.añadir(new Empleado(0L,"pepe", "pepe@gmail.com",
			// 1000d, true, Genero.MASCULINO));
			// empleadoService.añadir(new Empleado(0L, "ana", "ana@gmail.com",
			// 2000d, true,Genero.FEMENINO));
			// empleadoService.añadir(new Empleado(0L,"ramon", "ramon@gmail.com",
			// 1000d, true, Genero.MASCULINO));
			// empleadoService.añadir(new Empleado(0L, "luis", "luis@gmail.com",
			// 2000d, true,Genero.MASCULINO));
		};
	}
}
