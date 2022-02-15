package com.example.ejercicio8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class controlador {

  @Autowired VariablesProperties variablesProperties;

  @Autowired VariablesYML variablesYML;

  @GetMapping("/properties/valores")
  public String devolverValoresProperties() {
    return "Valor de var1 es: "
        + variablesProperties.getVar1()
        + " y el valor de my.var2 es "
        + variablesProperties.getVar2();
  }

  @GetMapping("/properties/var3")
  public String obtenerVar3Properties() {
    return "El valor de var3 es: " + variablesProperties.getVar3();
  }

  @GetMapping("/yml/valores")
  public String devolverValoresYml() {
    return "Valor de var1 es: "
        + variablesYML.getVar1()
        + " y el valor de my.var2 es "
        + variablesYML.getMy_var2();
  }

  @GetMapping("/yml/var3")
  public String obtenerVar3Yml() {
    return "El valor de var3 es: " + Optional.ofNullable(variablesYML.getVar3()).orElse("var3 no tiene valor");
  }
}
