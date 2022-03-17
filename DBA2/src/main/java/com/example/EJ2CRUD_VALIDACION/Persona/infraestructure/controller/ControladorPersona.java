package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller;

import com.example.EJ2CRUD_VALIDACION.Persona.application.IPersona;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class ControladorPersona {

  @Autowired IPersona personaService;



  public static final String GREATER_THAN = "greater";
  public static final String LESS_THAN = "less";
  public static final String EQUAL = "equal";

  @PostMapping()
  public PersonaOutputDTO addPersona(@RequestBody PersonaImputDTO personaDTO) throws Exception {
    return personaService.añadirUsuario(personaDTO);
  }

  @GetMapping("{id}")
  public PersonaOutputDTO findById(@PathVariable Integer id) throws Exception {

    return personaService.findById(id);
  }

  @GetMapping()
  public List<PersonaOutputDTO> getAll() {
    return personaService.getAllPersonas();
  }

  @DeleteMapping("{id}")
  public void borrarPersona(@PathVariable Integer id) throws Exception {
    personaService.deletePersona(id);
  }

  @PutMapping("update/{id}")
  public PersonaOutputDTO actualizarUsuario(
      @PathVariable Integer id, @RequestBody PersonaImputDTO personaImputDTO) throws Exception {
    return personaService.updatePersona(id, personaImputDTO);
  }


  @GetMapping("{usuario}/usuario")
  public List<PersonaOutputDTO> findByUser(@PathVariable String usuario){
    return personaService.findByUser(usuario);
  }


}
