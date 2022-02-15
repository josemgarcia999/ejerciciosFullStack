package com.example.ejercicio9;

import org.springframework.stereotype.Component;

@Component
public class perfil2 implements Perfil {
    String perfil = "perfil1";
    public void mifuncion() {
        System.out.println("Este texto corresponde al perfil "+ perfil);
    }

}
