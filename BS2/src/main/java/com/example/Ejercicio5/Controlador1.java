package com.example.Ejercicio5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    // SIEMPRE HACER AUTOWIRED DE INTERFACES, NO DE CLASES NORMALES
    @Autowired
    @Qualifier("PersonaServiceImpl")
    PersonaService personaService;

    @Autowired
    @Qualifier("bean1")
    PersonaService personaService1;

    @Autowired
    @Qualifier("bean2")
    PersonaService personaService2;

    @Autowired
    @Qualifier("bean3")
    PersonaService personaService3;


    @Autowired
    CiudadService ciudadService;



/*
Forma 2: Quitar autowired y hacer un constructor pasandolo por parametro
    public Controlador1(PersonaService personaService){
        this.personaService = personaService;
    }

 */
    @GetMapping("prueba/{var1}")
    String metodoPrueba(@PathVariable String var1){
        return "HolaMundo "+ var1;

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

    @GetMapping ("bean/{bean}")
    public void personaConBean(@PathVariable String bean){
    //return "Hola la variable vale "+bean;
        if (bean.equalsIgnoreCase("bean1")){
            personaService1.toString();
        }
        if (bean.equalsIgnoreCase("bean2")){
            personaService2.toString();
        }
        if(bean.equalsIgnoreCase("bean3")){
            personaService3.toString();
        }
        if (bean.equalsIgnoreCase("PersonaServiceImp")){
            personaService.toString();
        }

    }



}
