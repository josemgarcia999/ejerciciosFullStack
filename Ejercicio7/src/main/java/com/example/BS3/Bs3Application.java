package com.example.BS3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Bs3Application {

	public static void main(String[] args) {
		SpringApplication.run(Bs3Application.class, args);



	}

	@Bean
	claseSecundaria ejecutaClaseSegunda(){
		claseSecundaria c = new claseSecundaria();
		return c;
	}
	@Bean
	claseTercera ejecutaClaseTercera(){
		claseTercera c = new claseTercera();
		return c;
	}

}
