package com.example.Ejercicio5;

import org.springframework.beans.factory.annotation.Autowired;

public interface PersonaService {


    public String obtenerNombre();
    void ponerNombre(String nombre);
    Persona crearPersona(String nombre, int edad, String ciudad);
    Persona multiplicarEdad();

}
