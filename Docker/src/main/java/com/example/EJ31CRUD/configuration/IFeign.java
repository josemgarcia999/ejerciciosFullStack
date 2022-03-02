package com.example.EJ31CRUD.configuration;

import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.output.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="simpleFeign",url="http://localhost:8081/")
public interface IFeign {
    @GetMapping("/profesor/{id}")
    ResponseEntity<ProfesorOutputDTO> callServer(@PathVariable("id") String id);
}