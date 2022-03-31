package com.example.EJ2CRUD_VALIDACION.Persona.application;

import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.NotFoundException;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.UnprocesableException;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaInputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona {

  @Autowired PersonaRepo personaRepo;

  @Override
  public PersonaOutputDTO añadirUsuario(PersonaInputDTO persona) throws Exception {
    persona.comprobarDTO();
    PersonaEntity p = new PersonaEntity(persona);
    personaRepo.save(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public PersonaOutputDTO findById(Integer id) throws Exception {
    PersonaEntity p =
        personaRepo
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Usuario con Id: " + id + " no encontrado"));
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public List<PersonaOutputDTO> getAllPersonas() {
    List<PersonaEntity> personasEntities = personaRepo.findAll();
    List<PersonaOutputDTO> personas = new ArrayList<>();
    for (PersonaEntity p : personasEntities) {
      PersonaOutputDTO per = new PersonaOutputDTO(p);
      personas.add(per);
    }
    return personas;
  }

  @Override
  public List<PersonaOutputDTO> findByUsuario(String usuario) {
    List<PersonaEntity> personas = personaRepo.findAll();
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
    personaRepo.delete(
        personaRepo
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Usuario con Id: " + id + " no encontrado")));
  }

  @Override
  public PersonaOutputDTO updatePersona(Integer id, PersonaInputDTO personaInputDTO)
      throws Exception {

    PersonaEntity p = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe el ID"));
   // if (personaInputDTO.getUsuario().length() > 6 && personaInputDTO.getUsuario().length() < 10) {
      p.setUsuario(personaInputDTO.getUsuario());
    //}else {
      //throw new UnprocesableException("No se puede modificar el usuario");
    //}

    if (personaInputDTO.getPassword() != null && personaInputDTO.getPassword() !="" ) {
      p.setPassword(personaInputDTO.getPassword());
    }else {
      throw new UnprocesableException("Contraseña está vacío");
    }
    if (personaInputDTO.getName() != null && personaInputDTO.getName() !="") {
      p.setName(personaInputDTO.getName());
    }else {
      throw new UnprocesableException("Nombre está vacío");
    }
    if (personaInputDTO.getSurname() != null && personaInputDTO.getSurname() !="") {
      p.setSurname(personaInputDTO.getSurname());
    }else {
      throw new UnprocesableException("Apellidos está vacío");
    }
    if (personaInputDTO.getCompany_email() != null && personaInputDTO.getCompany_email() != "") {
      p.setCompany_email(personaInputDTO.getCompany_email());
    }else {
      throw new UnprocesableException("Company email está vacío");
    }
    if (personaInputDTO.getPersonal_email() != null && personaInputDTO.getPersonal_email() !="") {
      p.setPersonal_email(personaInputDTO.getPersonal_email());
    }else {
      throw new UnprocesableException("PersonalEmail está vacío");
    }
    if (personaInputDTO.getCity() != null && personaInputDTO.getCity() !="") {
      p.setCity(personaInputDTO.getCity());
    }else {
      throw new UnprocesableException("Ciudad está vacío");
    }

    if (personaInputDTO.getImagen_url() != null && personaInputDTO.getImagen_url() !="") {
      p.setImagen_url(personaInputDTO.getImagen_url());
    }else {
      throw new UnprocesableException("URl está vacío");
    }

    if (personaInputDTO.getTermination_date() != null) {
      p.setTermination_date(personaInputDTO.getTermination_date());
    }else {
      throw new UnprocesableException("TerminationDate está vacío");
    }

    personaRepo.saveAndFlush(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public void deleteAll() {
    personaRepo.deleteAll();
  }
}
