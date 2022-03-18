package com.example.EJ2CRUD_VALIDACION.Persona.application;

import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.NotFoundException;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.exceptions.UnprocesableException;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService implements IPersona {



  @Autowired
  MongoTemplate mongoTemplate;


  @Override
  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception {
    persona.comprobarDTO();
    PersonaEntity p = new PersonaEntity(persona);
    mongoTemplate.save(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public PersonaOutputDTO findById(Integer id) throws Exception {
    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    PersonaEntity p = mongoTemplate.findOne(query, PersonaEntity.class);
    return new PersonaOutputDTO(p);
  }

  @Override
  public List<PersonaOutputDTO> getAllPersonas() {
    List<PersonaEntity> personasEntities = mongoTemplate.findAll(PersonaEntity.class);
    List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
    for (PersonaEntity p : personasEntities) {
        PersonaOutputDTO persona = new PersonaOutputDTO(p);
        personaOutputDTOList.add(persona);

    }
    return personaOutputDTOList;
  }

  @Override
  public List<PersonaOutputDTO> findByUsuario(String usuario) {
    Query query = new Query();
    query.addCriteria(Criteria.where("usuario").is(usuario));
    List<PersonaEntity> personas =     mongoTemplate.find(query, PersonaEntity.class);
    List<PersonaOutputDTO> personaOutputDTOS = new ArrayList<>();
    for (PersonaEntity p : personas) {
        PersonaOutputDTO persona = new PersonaOutputDTO(p);
        personaOutputDTOS.add(persona);
    }
    return personaOutputDTOS;
  }

  @Override
  public void deletePersona(Integer id) throws Exception {
    mongoTemplate.remove(mongoTemplate.findById(id,PersonaEntity.class));
  }

  @Override
  public PersonaOutputDTO updatePersona(Integer id, PersonaImputDTO personaImputDTO)
      throws Exception {

    PersonaEntity p = mongoTemplate.findById(id,PersonaEntity.class);
   //if (personaImputDTO.getUsuario().length() > 6 && personaImputDTO.getUsuario().length() < 10) {
      p.setUsuario(personaImputDTO.getUsuario());
    //}else {
    //  throw new UnprocesableException("No se puede modificar el usuario");
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

    mongoTemplate.save(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }
}
