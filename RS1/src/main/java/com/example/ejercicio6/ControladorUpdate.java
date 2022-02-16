package com.example.ejercicio6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class ControladorUpdate {


    @Autowired
    PersonaService personaService;


    @PutMapping("/{id}")
    public Persona modificarPersona(@PathVariable int id, @RequestBody Persona persona){
        return personaService.modificarPersona(id,persona);


    }



}
