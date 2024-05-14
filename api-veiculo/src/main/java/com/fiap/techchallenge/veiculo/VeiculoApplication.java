package com.fiap.techchallenge.veiculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VeiculoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeiculoApplication.class, args);
	}

}
