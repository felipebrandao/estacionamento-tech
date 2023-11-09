package com.fiap.techchallenge.estacionamentotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EstacionamentoTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamentoTechApplication.class, args);
	}

}
