package com.example.Ejercicio4;

import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class Controlador1 {

    @GetMapping("/user/{nombre}")
    public String getHolaMundo(@PathVariable String nombre){
        return "Hola mundo "+nombre;

    }
    @GetMapping("/b")
    public String getHolaMundoB(){
        return "Hola mundo bbb";

    }

    @PostMapping("/useradd")
    public Persona useradd(){
        Persona p = new Persona ("Jose Manuel",22,"Úbeda");
        p.setEdad(p.getEdad()+1);
        return p;



    }

}
