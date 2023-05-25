package com.example.demo;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroservicioAccountFApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAccountFApplication.class, args);
	}


}
