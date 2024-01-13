package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Cuenta;
import com.example.demo.services.CuentaService;
import com.example.demo.services.MovimientoService;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	CuentaService cuentaService;
	@Autowired
	MovimientoService movimientoService;

	@Bean
	public CommandLineRunner metodo(){
		return args ->{
			cuentaService.guardarCuenta(new Cuenta("77778888","Indra",(double)8000,null));
			cuentaService.guardarCuenta(new Cuenta("55555666","Repsol",(double)10000,null));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
