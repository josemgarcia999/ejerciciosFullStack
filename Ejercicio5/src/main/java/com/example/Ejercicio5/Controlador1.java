package com.example.Ejercicio5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    @Autowired // SIEMPRE HACER AUTOWIRED DE INTERFACES, NO DE CLASES NORMALES
    PersonaService personaService;
/*
Forma 2: Quitar autowired y hacer un constructor pasandolo por parametro
    public Controlador1(PersonaService personaService){
        this.personaService = personaService;
    }

 */

    @GetMapping("/addPersona")
    public Persona addPersona(@RequestHeader String nombre, @RequestHeader int edad,@RequestHeader String ciudad){
        return personaService.crearPersona(nombre,edad,ciudad);

    }



}
