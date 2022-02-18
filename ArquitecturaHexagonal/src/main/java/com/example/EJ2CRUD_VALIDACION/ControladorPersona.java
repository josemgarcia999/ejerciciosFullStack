package com.example.EJ2CRUD_VALIDACION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorPersona {

  @Autowired IPersona personaService;

  @GetMapping("/prueba")
  public String prueba() {
    return "hola mundo";
  }

  @PostMapping("addPersona")
  public PersonaOutputDTO addPersona(@RequestBody PersonaImputDTO personaDTO) throws Exception {
    return personaService.añadirUsuario(personaDTO);
  }

  @GetMapping("buscarPersonaID/{id}")
  public PersonaOutputDTO buscarPersonaID(@PathVariable Integer id) {
    return personaService.buscarPersonaID(id);
  }

  @GetMapping("mostrarPersonas")
  public List<PersonaOutputDTO> mostrarPersonas() {
    return personaService.mostrarTodasPersonas();
  }

  @GetMapping("buscarPersonaUsuario/{usuario}")
  public List<PersonaOutputDTO> buscarPersonaUsuario(@PathVariable String usuario) {
    return personaService.buscarPersonaUsuario(usuario);
  }

  @DeleteMapping("{id}")
  public void borrarPersona(@PathVariable Integer id) throws Exception {
    personaService.borrarUsuario(id);
  }

  @PutMapping("actualizar/{id}")
  public PersonaOutputDTO actualizarUsuario(@PathVariable Integer id, @RequestBody PersonaImputDTO personaImputDTO) throws Exception {
    return personaService.modificarPersona(id,personaImputDTO);
  }



}


