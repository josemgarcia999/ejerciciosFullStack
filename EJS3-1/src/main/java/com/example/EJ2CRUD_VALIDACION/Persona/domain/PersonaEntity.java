package com.example.EJ2CRUD_VALIDACION.Persona.domain;

import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
  Boolean admin;

  public PersonaEntity(PersonaImputDTO personaDTO) throws Exception {
    if (personaDTO == null) return;
    setId(personaDTO.getId());

    setUsuario(personaDTO.getUsuario());

    setPassword(personaDTO.getPassword());

    setName(personaDTO.getName());

    setSurname(personaDTO.getSurname());

    setCompany_email(personaDTO.getCompany_email());

    setPersonal_email(personaDTO.getPersonal_email());

    setCity(personaDTO.getCity());

    setActive(personaDTO.getActive());

    setCreated_date(personaDTO.getCreated_date());

    setImagen_url(personaDTO.getImagen_url());

    setTermination_date(personaDTO.getTermination_date());

    setAdmin(personaDTO.getAdmin());
  }
}
