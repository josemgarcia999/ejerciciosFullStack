package com.example.EJ2CRUD_VALIDACION;

import com.example.EJ2CRUD_VALIDACION.Persona.application.PersonaService;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@SpringBootApplication
public class Ej2CrudValidacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ej2CrudValidacionApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}



	@Bean
	CommandLineRunner run(PersonaService personaService){

		return args -> {
			Date date1 = new Date();
			PersonaEntity p1 = new PersonaEntity(1, "josemg999", "0000"
					, "Jose", "García", "josemgarcia999@gmail.com", "josemgarcia999@gmail.com", "Úbeda"
					, true, date1, "url1", date1, true);
			PersonaEntity p2 = new PersonaEntity(2, "juanmy1234", "0000"
					, "Juanmy", "Villacreces", "juanmy1234@gmail.com", "juanmy1234@gmail.com", "Jaén"
					, true, date1, "url2", date1, false);

			personaService.añadirUsuario(new PersonaImputDTO(p1));
			personaService.añadirUsuario(new PersonaImputDTO(p2));
		};

	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
			}
		};
	}
}
