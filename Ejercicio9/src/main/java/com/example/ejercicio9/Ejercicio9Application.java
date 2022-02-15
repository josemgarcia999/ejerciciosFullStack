package com.example.ejercicio9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.Collections;

import static java.util.Collections.singletonMap;

@SpringBootApplication
@Profile("perfil1")
public class Ejercicio9Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio9Application.class, args);
		System.setProperty("spring.profiles.active", "perfil1");

		SpringApplication app = new SpringApplication(Ejercicio9Application.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
		app.run(args);

	}
}
