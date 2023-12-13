package com.example.myshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myshop.domain.Categoria;
import com.example.myshop.domain.Producto;
import com.example.myshop.domain.TipoIva;
import com.example.myshop.services.CategoriaService;
import com.example.myshop.services.ProductoService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(ProductoService productoService, CategoriaService categoriaService) {


		Categoria cat1 = categoriaService.añadir(new Categoria(0L,"Ordenadores"));
		Categoria cat2 = categoriaService.añadir(new Categoria(0L,"Impresoras"));
		return args -> {
			productoService.añadir(new Producto(0L, "Asus ChromeBook", true, TipoIva.REDUCIDO, 1000d,cat1));
			productoService.añadir(new Producto(0L, "Lenovo IdeaPad", false, TipoIva.NORMAL, 2000d,cat1));

			productoService.añadir(new Producto(0L, "HP ColorTank", true, TipoIva.SUPERREDUCIDO, 500d,cat2));
			productoService.añadir(new Producto(0L, "Canon Pixma", false, TipoIva.REDUCIDO, 800d,cat2));

			categoriaService.añadir(new Categoria(0L, "Ordenadores"));
			categoriaService.añadir(new Categoria(0L, "Impresoras"));

		};

	}
}
