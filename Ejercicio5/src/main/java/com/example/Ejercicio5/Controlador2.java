package com.example.Ejercicio5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    @Autowired
    PersonaService personaService;

    @Autowired
    CiudadService ciudadService;

    @GetMapping("/getPersona")
    public Persona getPersona(@RequestHeader String nombre, @RequestHeader int edad, @RequestHeader String ciudad){
         return personaService.multiplicarEdad();
    }
    @GetMapping("/getCiudades")
    public ArrayList<Ciudad> devolverCiudades(){
        return ciudadService.devolverCiudades();
    }



}
