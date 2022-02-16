package com.example.ejercicio6;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
public class ControladorDelete {

    @Autowired
    PersonaService personaService;

    @DeleteMapping("{id}")
    public void borrarPersona(@PathVariable int id){
        personaService.borrarPersona(id);

    }


}
