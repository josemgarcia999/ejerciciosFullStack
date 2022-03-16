package com.example.EJ2CRUD_VALIDACION.Persona.application;

import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;

import java.util.Date;
import java.util.List;

public interface IPersona {

  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception;

  public PersonaOutputDTO findById(Integer id) throws Exception;

  public List<PersonaOutputDTO> getAllPersonas();

  public List<PersonaOutputDTO> findByUsuario(String usuario);

  public void deletePersona(Integer id) throws Exception;

  public PersonaOutputDTO updatePersona(Integer id, PersonaImputDTO personaImputDTO)
      throws Exception;
  public List<PersonaOutputDTO> getData(
          String usuario,
          String name,
          String surname,
          Date created_date,
          String dateCondition,
          String ordenar,
          Integer pagina);
}
