package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Cuenta;
import com.example.demo.services.BancoServicio;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	BancoServicio bancoServicio;

	@Bean
	public CommandLineRunner metodo(){
		return args ->{
			bancoServicio.nuevaCuenta(new Cuenta("77778888","Raul",(double)8000));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
