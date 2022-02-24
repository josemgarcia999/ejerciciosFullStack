package com.example.EJ31CRUD.Persona.application;

import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;

import java.util.List;

public interface IPersona {

  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception;

  public PersonaOutputDTO findById(Integer id,String parametro) throws Exception;

  public List<PersonaOutputDTO> getAllPersonas(String parametro);

  public List<PersonaOutputDTO> findByUsuario(String usuario,String parametro);

  public void deletePersona(Integer id) throws Exception;

  public PersonaOutputDTO updatePersona(Integer id, PersonaImputDTO personaImputDTO)
      throws Exception;
}
