package com.example.Ejercicio4;

import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.*;

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
    public Persona useradd(@RequestBody Persona p){
        p.setEdad(p.getEdad()+1);
        return p;



    }


}
