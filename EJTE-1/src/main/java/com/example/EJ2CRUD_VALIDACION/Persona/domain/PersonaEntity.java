package com.example.EJ2CRUD_VALIDACION.Persona.domain;

import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  public PersonaEntity(PersonaInputDTO personaDTO) throws Exception {
    if (personaDTO == null) return;
    //setId(personaDTO.getId());

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

  }

  public PersonaEntity(String usuario, String password, String name, String surname, String company_email, String personal_email, String city, Boolean active, Date created_date, String imagen_url, Date termination_date) {
    this.usuario = usuario;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.company_email = company_email;
    this.personal_email = personal_email;
    this.city = city;
    this.active = active;
    this.created_date = created_date;
    this.imagen_url = imagen_url;
    this.termination_date = termination_date;
  }


}
