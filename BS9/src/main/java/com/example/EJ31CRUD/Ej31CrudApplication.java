package com.example.EJ31CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ej31CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ej31CrudApplication.class, args);
	}

}
