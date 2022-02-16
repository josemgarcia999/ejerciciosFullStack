package com.example.ejercicio9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controlador {


  @Value("${url}")
  String url;

  @Value("${password}")
  String password;

  @Autowired
  MiConfiguracion miConfiguracion = new MiConfiguracion();

  @Autowired
  perfil perfil;

  @GetMapping("/parametros")
  public String devolverParametros() {
    return "Url: " + url + "\n" + "Password: " + password;
  }

  @GetMapping("/miconfiguracion")
  public String devolverConfiguracion() {
    return "Url: "
        + url
        + "\n"
        + "Password: "
        + password
        + "\n"
        + "Valor1: "
        + miConfiguracion.getValor1()
        + "\n"
        + "Valor2 "
        + miConfiguracion.getValor2();
  }

  @GetMapping("/perfil")
  public void ejecutarPerfil() {
    perfil.mifuncion();
  }
}
