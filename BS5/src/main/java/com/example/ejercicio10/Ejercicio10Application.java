package com.example.ejercicio10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class Ejercicio10Application {

	public static void main(String[] args) {

		SpringApplication.run(Ejercicio10Application.class, args);

	}
	@PostConstruct
	private void pruebaLog(){
		log.trace("A TRACE Message");
		log.debug("A DEBUG Message");
		log.info("An INFO Message");
		log.warn("A WARN Message");
		log.error("An ERROR Message");
	}
}
