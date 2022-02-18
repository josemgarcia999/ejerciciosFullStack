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

  @NonNull String usuario;
  @NonNull String password;
  @NonNull String name;
  @NonNull String surname;
  @NonNull String company_email;
  @NonNull String personal_email;
  @NonNull String city;
  @NonNull Boolean active;
  Date created_date;

  String imagen_url;

  Date termination_date;

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
  }


}
