package com.example.EJ2CRUD_VALIDACION;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IPersona {

  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception;

  public PersonaOutputDTO buscarPersonaID(Integer id);

  public List<PersonaOutputDTO> mostrarTodasPersonas();

  public List<PersonaOutputDTO> buscarPersonaUsuario(String nombre);

  public void borrarUsuario(Integer id) throws Exception;

  public PersonaOutputDTO modificarPersona(Integer id, PersonaImputDTO personaImputDTO)
      throws Exception;
}
