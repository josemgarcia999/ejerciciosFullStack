package com.example.ejercicio6;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/persona")
public class ControladorCreate {

    @Autowired
    PersonaService personaService;
    private final AtomicInteger count = new AtomicInteger(0);

    @PostMapping
    public Persona addPersona(@RequestHeader String nombre, @RequestHeader String poblacion,@RequestHeader int edad){
       Persona p = personaService.crearPersona(count.incrementAndGet(),nombre, poblacion, edad);
       return p;
    }





}
