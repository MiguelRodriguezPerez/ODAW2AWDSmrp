package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Producto;
import com.example.demo.domain.TipoIva;
import com.example.demo.services.ProductoService;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	ProductoService servicioTienda;
	@Bean
	public CommandLineRunner metodo() {
		return args -> {
			servicioTienda.nuevoProducto(new Producto((long)55,"Ladrillo",false,TipoIva.GENERAL,(double)60));
			servicioTienda.nuevoProducto(new Producto((long)53,"Bloque",true,TipoIva.GENERAL,(double)60));
			servicioTienda.nuevoProducto(new Producto((long)54,"Juan",false,TipoIva.GENERAL,(double)60));

		};
}	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
