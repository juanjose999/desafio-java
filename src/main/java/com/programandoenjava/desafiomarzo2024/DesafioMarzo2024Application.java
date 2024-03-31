package com.programandoenjava.desafiomarzo2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class DesafioMarzo2024Application {

	public static void main(String[] args) {
		SpringApplication.run(DesafioMarzo2024Application.class, args);
	}

}
