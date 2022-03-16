package com.example.EJ2CRUD_VALIDACION.Persona.domain;

import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class PersonaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Integer id;

  String usuario;
  String password;
  String name;
  String surname;
  String company_email;
  String personal_email;
  String city;
  Boolean active;
  Date created_date;

  String imagen_url;

  Date termination_date;

  public PersonaEntity(PersonaImputDTO personaDTO) throws Exception {
    if (personaDTO == null) return;
    setId(personaDTO.id());

    setUsuario(personaDTO.usuario());

    setPassword(personaDTO.password());

    setName(personaDTO.name());

    setSurname(personaDTO.surname());

    setCompany_email(personaDTO.company_email());

    setPersonal_email(personaDTO.personal_email());

    setCity(personaDTO.city());

    setActive(personaDTO.active());

    setCreated_date(personaDTO.created_date());

    setImagen_url(personaDTO.imagen_url());

    setTermination_date(personaDTO.termination_date());
  }
}
