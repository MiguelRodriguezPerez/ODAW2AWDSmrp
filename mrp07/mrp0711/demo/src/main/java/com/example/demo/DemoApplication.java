package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Cuenta;
import com.example.demo.services.BancoServicioImplBD;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	BancoServicioImplBD bancoServicio;

	@Bean
	public CommandLineRunner metodo(){
		return args ->{
			bancoServicio.nuevaCuenta(new Cuenta("77778888","Indra",(double)8000,null));
			bancoServicio.nuevaCuenta(new Cuenta("55555666","Repsol",(double)10000,null));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
