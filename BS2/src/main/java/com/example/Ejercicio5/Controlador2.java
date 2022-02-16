package com.example.Ejercicio5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    @Autowired
    @Qualifier("PersonaServiceImpl")
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
    @DeleteMapping("/deleteCiudades")
    public void eliminarCiudades(){
        ciudadService.eliminarCiudades();
    }


}
