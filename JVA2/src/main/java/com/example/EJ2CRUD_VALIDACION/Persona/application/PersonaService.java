package com.example.EJ2CRUD_VALIDACION.Persona.application;

import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.NotFoundException;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.UnprocesableException;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
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
  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception {
    //persona.comprobarDTO();
    PersonaEntity p = new PersonaEntity(persona);
    personaRepo.save(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p.getId(),p.getUsuario(),p.getPassword(),p.getName(),p.getSurname(),p.getCompany_email(),p.getPersonal_email(),p.getCity(),p.getActive(),p.getCreated_date(),p.getImagen_url(),p.getTermination_date());
    return personaOutputDTO;
  }

  @Override
  public PersonaOutputDTO findById(Integer id) throws Exception {
    PersonaEntity p =
        personaRepo
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Usuario con Id: " + id + " no encontrado"));
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p.getId(),p.getUsuario(),p.getPassword(),p.getName(),p.getSurname(),p.getCompany_email(),p.getPersonal_email(),p.getCity(),p.getActive(),p.getCreated_date(),p.getImagen_url(),p.getTermination_date());
    return personaOutputDTO;
  }

  @Override
  public List<PersonaOutputDTO> getAllPersonas() {
    List<PersonaEntity> personasEntities = personaRepo.findAll();
    List<PersonaOutputDTO> personas = new ArrayList<>();
    for (PersonaEntity p : personasEntities) {
      PersonaOutputDTO per = new PersonaOutputDTO(p.getId(),p.getUsuario(),p.getPassword(),p.getName(),p.getSurname(),p.getCompany_email(),p.getPersonal_email(),p.getCity(),p.getActive(),p.getCreated_date(),p.getImagen_url(),p.getTermination_date());
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
        PersonaOutputDTO persona = new PersonaOutputDTO(p.getId(),p.getUsuario(),p.getPassword(),p.getName(),p.getSurname(),p.getCompany_email(),p.getPersonal_email(),p.getCity(),p.getActive(),p.getCreated_date(),p.getImagen_url(),p.getTermination_date());
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
  public PersonaOutputDTO updatePersona(Integer id, PersonaImputDTO personaImputDTO)
      throws Exception {

    PersonaEntity p = personaRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe el ID"));
   // if (personaImputDTO.getUsuario().length() > 6 && personaImputDTO.getUsuario().length() < 10) {
      p.setUsuario(personaImputDTO.usuario());
    //}else {
      //throw new UnprocesableException("No se puede modificar el usuario");
    //}

    if (personaImputDTO.password() != null && personaImputDTO.password() !="" ) {
      p.setPassword(personaImputDTO.password());
    }else {
      throw new UnprocesableException("Contraseña está vacío");
    }
    if (personaImputDTO.name() != null && personaImputDTO.name() !="") {
      p.setName(personaImputDTO.name());
    }else {
      throw new UnprocesableException("Nombre está vacío");
    }
    if (personaImputDTO.surname() != null && personaImputDTO.surname() !="") {
      p.setSurname(personaImputDTO.surname());
    }else {
      throw new UnprocesableException("Apellidos está vacío");
    }
    if (personaImputDTO.company_email() != null && personaImputDTO.company_email() != "") {
      p.setCompany_email(personaImputDTO.company_email());
    }else {
      throw new UnprocesableException("Company email está vacío");
    }
    if (personaImputDTO.personal_email() != null && personaImputDTO.personal_email() !="") {
      p.setPersonal_email(personaImputDTO.personal_email());
    }else {
      throw new UnprocesableException("PersonalEmail está vacío");
    }
    if (personaImputDTO.city() != null && personaImputDTO.city() !="") {
      p.setCity(personaImputDTO.city());
    }else {
      throw new UnprocesableException("Ciudad está vacío");
    }

    if (personaImputDTO.imagen_url() != null && personaImputDTO.imagen_url() !="") {
      p.setImagen_url(personaImputDTO.imagen_url());
    }else {
      throw new UnprocesableException("URl está vacío");
    }

    if (personaImputDTO.termination_date() != null) {
      p.setTermination_date(personaImputDTO.termination_date());
    }else {
      throw new UnprocesableException("TerminationDate está vacío");
    }

    personaRepo.saveAndFlush(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p.getId(),p.getUsuario(),p.getPassword(),p.getName(),p.getSurname(),p.getCompany_email(),p.getPersonal_email(),p.getCity(),p.getActive(),p.getCreated_date(),p.getImagen_url(),p.getTermination_date());
    return personaOutputDTO;
  }
}
