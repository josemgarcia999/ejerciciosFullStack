package com.example.ejercicio9;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Profile({"perfil2"})
@Component
public class perfil2 implements perfil {
  String perfil="perfil2";


  public void mifuncion() {
    System.out.println("Este texto corresponde al perfil " + perfil);
  }
}
