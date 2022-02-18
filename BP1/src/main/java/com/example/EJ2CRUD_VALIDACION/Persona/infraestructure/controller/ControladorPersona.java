package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller;

import com.example.EJ2CRUD_VALIDACION.Persona.application.IPersona;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorPersona {

  @Autowired
  IPersona personaService;

  @PostMapping()
  public PersonaOutputDTO addPersona(@RequestBody PersonaImputDTO personaDTO) throws Exception {
    return personaService.añadirUsuario(personaDTO);
  }

  @GetMapping("{id}")
  public PersonaOutputDTO findById(@PathVariable Integer id) {
    return personaService.findById(id);
  }

  @GetMapping()
  public List<PersonaOutputDTO> getAll() {
    return personaService.getAllPersonas();
  }

  @GetMapping("{usuario}/usuario")
  public List<PersonaOutputDTO> buscarPersonaUsuario(@PathVariable String usuario) {
    return personaService.findByUsuario(usuario);
  }

  @DeleteMapping("{id}")
  public void borrarPersona(@PathVariable Integer id) throws Exception {
    personaService.deletePersona(id);
  }

  @PutMapping("update/{id}")
  public PersonaOutputDTO actualizarUsuario(@PathVariable Integer id, @RequestBody PersonaImputDTO personaImputDTO) throws Exception {
    return personaService.updatePersona(id,personaImputDTO);
  }



}


