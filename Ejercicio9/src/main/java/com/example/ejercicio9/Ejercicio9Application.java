package com.example.ejercicio9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Collections;

@SpringBootApplication
public class Ejercicio9Application {
	public static void main(String[] args) {
		SpringApplication.run(Ejercicio9Application.class, args);


	}

}
