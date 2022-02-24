package com.example.EJ31CRUD.Persona.infraestructure.controller;

import com.example.EJ31CRUD.Persona.application.IPersona;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
  public PersonaOutputDTO findById(@PathVariable Integer id, @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
          String outputType) throws Exception {
    return personaService.findById(id,outputType);
  }

  @GetMapping()
  public List<PersonaOutputDTO> getAll(
          @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
                  String outputType) {
    return personaService.getAllPersonas(outputType);
  }

  @GetMapping("{usuario}/usuario")
  public List<PersonaOutputDTO> buscarPersonaUsuario(@PathVariable String usuario, @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
          String outputType) {
    return personaService.findByUsuario(usuario,outputType);
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


