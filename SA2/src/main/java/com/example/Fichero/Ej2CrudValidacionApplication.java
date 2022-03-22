package com.example.Fichero;

import com.example.Fichero.application.IFichero;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@SpringBootApplication
public class Ej2CrudValidacionApplication implements CommandLineRunner {

	@Resource
	IFichero storageService;
	public static void main(String[] args) {
		SpringApplication.run(Ej2CrudValidacionApplication.class, args);
	}
	@Override
	public void run(String... arg) throws Exception {

    if (arg.length == 0) {
		storageService.deleteAll();
		storageService.init();
	}else{
		storageService.deleteAll();
		storageService.setPath(arg.toString());
		storageService.init();
	}

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
