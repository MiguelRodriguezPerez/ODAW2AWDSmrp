package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Empleo;
import com.example.demo.domain.Jornada;
import com.example.demo.services.EmpleosService;
import com.example.demo.services.QuienesSomosService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	QuienesSomosService wk;
	@Autowired
	EmpleosService es;

	@Override
	public void run(String... args) throws Exception {
		wk.otraEmpresa("Zara");
		wk.otraEmpresa("Ford");
		wk.otraEmpresa("Mercadona");
		wk.otraEmpresa("Ikea");

		es.otroEmpleo(new Empleo((long)01, "Albañil", "Construccion", (double)1500, Jornada.PARCIAL));
		es.otroEmpleo(new Empleo((long)02, "Administrativo", "Administracion", (double)1800, Jornada.COMPLETA));
		es.otroEmpleo(new Empleo((long)03, "Camarero", "Hosteleria", (double)1000, Jornada.PARCIAL));
		es.otroEmpleo(new Empleo((long)04, "Cocinero", "Hosteleria", (double)1200, Jornada.COMPLETA));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
