package com.example.Ejercicio5;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class Ejercicio5Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio5Application.class, args);


	}
	@Bean
	ArrayList<Ciudad> crearListaCiudades(){
		CiudadService cs = new CiudadServiceImp();
		ArrayList<Ciudad> ciudades = new ArrayList<>();
		return ciudades;
	}



	@Bean
	@Qualifier("bean1")
	PersonaService getPersonaServiceBean1(){
		PersonaService p = new PersonaServiceImp();
		p.ponerNombre("bean1");
		return p;
	}
	@Bean
	@Qualifier("bean2")
	PersonaService getPersonaServiceBean2(){
		PersonaService p = new PersonaServiceImp();
		p.ponerNombre("bean2");
		return p;
	}
	@Bean
	@Qualifier("bean3")
	PersonaService getPersonaService3(){
		PersonaService p = new PersonaServiceImp();
		p.ponerNombre("bean3");
		return p;
	}



	}

