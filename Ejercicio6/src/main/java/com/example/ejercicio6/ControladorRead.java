package com.example.ejercicio6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/persona")
public class ControladorRead {

    @Autowired
    PersonaService personaService;



    @GetMapping("mostrarPersonas")
    public ArrayList<Persona> mostrarPersonas(){
       return personaService.mostrarPersonas();

    }

    @GetMapping("/{id}")
    public Persona buscarPersonaId(@PathVariable int id){
       return personaService.buscarPersona(id);
    }
    @GetMapping("/nombre/{nombre}")
    public ArrayList<Persona> buscarPersonaNombre(@PathVariable String nombre){
        return personaService.buscarPersona(nombre);
    }





}
