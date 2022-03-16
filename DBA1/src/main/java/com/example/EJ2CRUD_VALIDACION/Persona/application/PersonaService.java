package com.example.EJ2CRUD_VALIDACION.Persona.application;

import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.NotFoundException;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.UnprocesableException;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona {

  @Autowired
  PersonaRepository personaRepository;

  @Override
  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception {
    persona.comprobarDTO();
    PersonaEntity p = new PersonaEntity(persona);
    personaRepository.save(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public PersonaOutputDTO findById(Integer id) throws Exception {
    PersonaEntity p =
        personaRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Usuario con Id: " + id + " no encontrado"));
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public List<PersonaOutputDTO> getAllPersonas() {
    List<PersonaEntity> personasEntities = personaRepository.findAll();
    List<PersonaOutputDTO> personas = new ArrayList<>();
    for (PersonaEntity p : personasEntities) {
      PersonaOutputDTO per = new PersonaOutputDTO(p);
      personas.add(per);
    }
    return personas;
  }

  @Override
  public List<PersonaOutputDTO> findByUsuario(String usuario) {
    List<PersonaEntity> personas = personaRepository.findAll();
    List<PersonaOutputDTO> personaOutputDTOS = new ArrayList<>();
    for (PersonaEntity p : personas) {
      if (p.getUsuario().equalsIgnoreCase(usuario)) {
        PersonaOutputDTO persona = new PersonaOutputDTO(p);
        personaOutputDTOS.add(persona);
      }
    }
    return personaOutputDTOS;
  }

  @Override
  public void deletePersona(Integer id) throws Exception {
    personaRepository.delete(
        personaRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Usuario con Id: " + id + " no encontrado")));
  }

  @Override
  public PersonaOutputDTO updatePersona(Integer id, PersonaImputDTO personaImputDTO)
      throws Exception {

    PersonaEntity p = personaRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe el ID"));
   // if (personaImputDTO.getUsuario().length() > 6 && personaImputDTO.getUsuario().length() < 10) {
      p.setUsuario(personaImputDTO.getUsuario());
    //}else {
      //throw new UnprocesableException("No se puede modificar el usuario");
    //}

    if (personaImputDTO.getPassword() != null && personaImputDTO.getPassword() !="" ) {
      p.setPassword(personaImputDTO.getPassword());
    }else {
      throw new UnprocesableException("Contraseña está vacío");
    }
    if (personaImputDTO.getName() != null && personaImputDTO.getName() !="") {
      p.setName(personaImputDTO.getName());
    }else {
      throw new UnprocesableException("Nombre está vacío");
    }
    if (personaImputDTO.getSurname() != null && personaImputDTO.getSurname() !="") {
      p.setSurname(personaImputDTO.getSurname());
    }else {
      throw new UnprocesableException("Apellidos está vacío");
    }
    if (personaImputDTO.getCompany_email() != null && personaImputDTO.getCompany_email() != "") {
      p.setCompany_email(personaImputDTO.getCompany_email());
    }else {
      throw new UnprocesableException("Company email está vacío");
    }
    if (personaImputDTO.getPersonal_email() != null && personaImputDTO.getPersonal_email() !="") {
      p.setPersonal_email(personaImputDTO.getPersonal_email());
    }else {
      throw new UnprocesableException("PersonalEmail está vacío");
    }
    if (personaImputDTO.getCity() != null && personaImputDTO.getCity() !="") {
      p.setCity(personaImputDTO.getCity());
    }else {
      throw new UnprocesableException("Ciudad está vacío");
    }

    if (personaImputDTO.getImagen_url() != null && personaImputDTO.getImagen_url() !="") {
      p.setImagen_url(personaImputDTO.getImagen_url());
    }else {
      throw new UnprocesableException("URl está vacío");
    }

    if (personaImputDTO.getTermination_date() != null) {
      p.setTermination_date(personaImputDTO.getTermination_date());
    }else {
      throw new UnprocesableException("TerminationDate está vacío");
    }

    personaRepository.saveAndFlush(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }


}
