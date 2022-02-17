package com.example.EJ2CRUD_VALIDACION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorPersona {


  @Autowired
  IPersona personaService;

  @GetMapping("/prueba")
  public String prueba() {
    return "hola mundo";
  }


  @PostMapping("/addPersona")
  public PersonaOutputDTO addPersona(@RequestBody PersonaImputDTO personaDTO) throws Exception {
    System.out.println("Estoy dentro del controlador");
    return personaService.añadirUsuario(personaDTO);
  }

  @GetMapping("/buscarPersonaID/{id}")
  public PersonaOutputDTO buscarPersonaID(@PathVariable Integer id){
    System.out.println("DENTRO DE BUSCAR PERSONA");
    return personaService.buscarPersonaID(id);
  }

  @GetMapping("/mostrarPersonas")
  public List<PersonaOutputDTO> mostrarPersonas(){
    return personaService.mostrarTodasPersonas();
  }

  @GetMapping("/buscarPersonaUsuario/{usuario}")
  public List<PersonaOutputDTO> buscarPersonaUsuario(@PathVariable String usuario){
    return personaService.buscarPersonaUsuario(usuario);
  }



}
