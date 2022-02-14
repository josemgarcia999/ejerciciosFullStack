package com.example.Ejercicio5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    @Autowired // SIEMPRE HACER AUTOWIRED DE INTERFACES, NO DE CLASES NORMALES
    PersonaService personaService;

    @Autowired
    CiudadService ciudadService;
/*
Forma 2: Quitar autowired y hacer un constructor pasandolo por parametro
    public Controlador1(PersonaService personaService){
        this.personaService = personaService;
    }

 */
    @GetMapping("prueba")
    String metodoPrueba(){
        return "HolaMundo";

    }


    @GetMapping("/addPersona")
    public Persona addPersona(@RequestHeader String nombre, @RequestHeader int edad,@RequestHeader String ciudad){
        return personaService.crearPersona(nombre,edad,ciudad);

    }

    @PostMapping ("/addCiudad")
    public void addCiudad(@RequestHeader String nombre, @RequestHeader int numHabitantes){
        Ciudad c = new Ciudad (nombre,numHabitantes);
        ciudadService.aniadirCiudad(c);
    }

}
