package com.example.Ejercicio5;

import java.util.ArrayList;

public interface CiudadService {

    void aniadirCiudad(Ciudad c);
    ArrayList<Ciudad> devolverCiudades();
    void eliminarCiudades();
}
