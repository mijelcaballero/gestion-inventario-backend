package com.gestion.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gestion.inventario", "net.javaguides.springboot"})
public class GestionInventarioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionInventarioBackendApplication.class, args);
	}

}
