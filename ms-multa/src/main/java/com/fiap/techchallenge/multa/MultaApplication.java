package com.fiap.techchallenge.multa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MultaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultaApplication.class, args);
	}

}
