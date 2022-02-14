package com.example.ejercicio6;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonaServiceImpl implements PersonaService{
    ArrayList<Persona> personas = new ArrayList<>();


    public Persona crearPersona(int id,String nombre,String poblacion,int edad){
        Persona p = new Persona(id,nombre,poblacion,edad);
        personas.add(p);
        return p;
    }
    public ArrayList<Persona> mostrarPersonas(){
    return personas;
    }


    public void borrarPersona(int id) {
        for (int i = 0; i< personas.size();i++){
            if(personas.get(i).getId() == id){
                personas.remove(i);
            }
        }

    }


    public Persona buscarPersona(int id) {
    for (int i = 0; i < personas.size(); i++) {
      if(personas.get(i).getId() == id)
          return personas.get(i);
    }
    return null;
    }

    public ArrayList<Persona> buscarPersona(String nombre){
        ArrayList<Persona> pNombre = new ArrayList<>();
    for (int i = 0; i < personas.size(); i++) {
        if (personas.get(i).getNombre().equalsIgnoreCase(nombre))
            pNombre.add(personas.get(i));
    }
    return pNombre;
    }


    public Persona modificarPersona(int id, Persona p){
        Persona per = buscarPersona(id);
        if(per != null){
            if (p.getNombre()!=null) per.setNombre(p.getNombre());
            if (p.getPoblacion()!=null) per.setNombre(p.getPoblacion());

        }
        return per;

    }


}
