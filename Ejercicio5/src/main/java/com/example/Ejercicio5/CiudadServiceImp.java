package com.example.Ejercicio5;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CiudadServiceImp implements CiudadService{
    ArrayList<Ciudad> listaCiudades = new ArrayList<>();

    public void aniadirCiudad(Ciudad c){
        listaCiudades.add(c);
    }
    public ArrayList<Ciudad> devolverCiudades(){
        return listaCiudades;

    }


}
