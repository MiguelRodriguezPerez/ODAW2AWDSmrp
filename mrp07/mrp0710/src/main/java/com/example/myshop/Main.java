package com.example.myshop;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myshop.domain.Categoria;
import com.example.myshop.domain.Producto;
import com.example.myshop.domain.TipoIva;
import com.example.myshop.domain.Usuario;
import com.example.myshop.domain.Valoracion;
import com.example.myshop.services.CategoriaService;
import com.example.myshop.services.ProductoService;
import com.example.myshop.services.UsuarioService;
import com.example.myshop.services.ValoracionService;


@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(ProductoService productoService, CategoriaService categoriaService,
	UsuarioService usuarioService, ValoracionService valoracionService) {


		Categoria cat1 = categoriaService.añadir(new Categoria(0L,"Ordenadores"));
		Categoria cat2 = categoriaService.añadir(new Categoria(0L,"Impresoras"));
		return args -> {
			productoService.añadir(new Producto(0L, "Asus ChromeBook", true, TipoIva.REDUCIDO, 1000d,cat1,null));
			productoService.añadir(new Producto(0L, "Lenovo IdeaPad", false, TipoIva.NORMAL, 2000d,cat1,null));

			productoService.añadir(new Producto(0L, "HP ColorTank", true, TipoIva.SUPERREDUCIDO, 500d,cat2,null));
			productoService.añadir(new Producto(0L, "Canon Pixma", false, TipoIva.REDUCIDO, 800d,cat2,null));

			categoriaService.añadir(new Categoria(0L, "Ordenadores"));
			categoriaService.añadir(new Categoria(0L, "Impresoras"));

			usuarioService.añadir(new Usuario(null, "Laura", LocalDate.of(2023,1,8),null));
			usuarioService.añadir(new Usuario(null, "Jacinto", LocalDate.of(2023,3,18),null));

			valoracionService.añadir(new Valoracion((long)55, productoService.obtenerPorId((long)1), usuarioService.obtenerPorId((long)1),4,"Excelente producto"));
			valoracionService.añadir(new Valoracion((long)56, productoService.obtenerPorId((long)2), usuarioService.obtenerPorId((long)2),2,"Se calienta mucho"));
		};

	}
}
