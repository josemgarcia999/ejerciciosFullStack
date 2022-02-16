package com.example.ejercicio9;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Profile({"perfil1","default"})
public class perfil1 implements perfil {
  String perfil="perfil1";

  public void mifuncion() {
    System.out.println("Este texto corresponde al perfil "+perfil);
  }
}
