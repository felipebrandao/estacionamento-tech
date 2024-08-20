package com.fiap.techchallenge.estacionamentoscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EstacionamentoSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstacionamentoSchedulerApplication.class, args);
	}

}
