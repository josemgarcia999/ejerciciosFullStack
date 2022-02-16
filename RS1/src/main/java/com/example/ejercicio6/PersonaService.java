package com.example.ejercicio6;

import java.util.ArrayList;

public interface PersonaService {

    Persona crearPersona(int id,String nombre,String poblacion,int edad);
    ArrayList<Persona> mostrarPersonas();
    void borrarPersona(int id);
    Persona buscarPersona(int id);
    ArrayList<Persona>  buscarPersona(String nombre);
    public Persona modificarPersona(int id, Persona p);

}
