package com.example.Ejercicio5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaServiceImp implements PersonaService {

    Persona person = new Persona();



    public String obtenerNombre(){
        return person.getNombre();

    }
    public void ponerNombre(String nombre) {
        person.setNombre(nombre);
    }

    public Persona crearPersona(String nombre, int edad, String ciudad){
        person.setNombre(nombre);
        person.setEdad(edad);
        person.setCiudad(ciudad);
        return person;
    }

    public Persona multiplicarEdad(){
        person.setEdad(person.getEdad()*2);
        return person;
    }

}
