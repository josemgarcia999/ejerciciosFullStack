package com.example.EJ2CRUD_VALIDACION;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
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

    if (personaDTO.getUsuario() == null) throw new Exception("Usuario no puede ser nulo");
    else if (personaDTO.getUsuario().length() < 6 || personaDTO.getUsuario().length() > 10)
      throw new Exception("Usuario debe tener entre 6 y 10 caracteres");
    else setUsuario(personaDTO.getUsuario());

    if (personaDTO.getPassword() == null) throw new Exception("Password no puede ser nulo");
    else setPassword(personaDTO.getPassword());

    if (personaDTO.getName() == null) throw new Exception("Name no puede ser nulo");
    else setName(personaDTO.getName());

    if (personaDTO.getSurname() == null) throw new Exception("Surname no puede ser nulo");
    else setSurname(personaDTO.getSurname());

    if (personaDTO.getCompany_email() == null) throw new Exception("Company_email no puede ser nulo");
    else setCompany_email(personaDTO.getCompany_email());

    if (personaDTO.getPersonal_email() == null) throw new Exception("Personal_email no puede ser nulo");
    else setPersonal_email(personaDTO.getPersonal_email());

    if (personaDTO.getCity() == null) throw new Exception("City no puede ser nulo");
    else setCity(personaDTO.getCity());

    if (personaDTO.getActive() == null) throw new Exception("Active no puede ser nulo");
    else setActive(personaDTO.getActive());

    if (personaDTO.getCreated_date() == null) throw new Exception("Created_date no puede ser nulo");
    else setCreated_date(personaDTO.getCreated_date());

    setImagen_url(personaDTO.getImagen_url());

    setTermination_date(personaDTO.getTermination_date());
  }
}
