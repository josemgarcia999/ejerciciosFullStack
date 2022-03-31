package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput;

import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaImputDTO implements Serializable{


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

    public void comprobarDTO() throws Exception {
        if (this.getUsuario() == null) throw new Exception("Usuario no puede ser nulo");

        if (this.getUsuario().length() < 6 || this.getUsuario().length() > 10)
            throw new Exception("Usuario debe tener entre 6 y 10 caracteres");

        if (this.getPassword() == null) throw new Exception("Password no puede ser nulo");

        if (this.getName() == null) throw new Exception("Name no puede ser nulo");

        if (this.getSurname() == null) throw new Exception("Surname no puede ser nulo");

        if (this.getCompany_email() == null)
            throw new Exception("Company_email no puede ser nulo");

        if (this.getPersonal_email() == null)
            throw new Exception("Personal_email no puede ser nulo");

        if (this.getCity() == null) throw new Exception("City no puede ser nulo");

        if (this.getActive() == null) throw new Exception("Active no puede ser nulo");

        if (this.getCreated_date() == null) throw new Exception("Created_date no puede ser nulo");

        if (this.getAdmin() == null) throw new Exception("Admin no puede ser nulo");

    }

    public PersonaImputDTO(PersonaEntity persona){
        if(persona==null)
            return;
        setUsuario(persona.getUsuario());
        setPassword(persona.getPassword());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getPersonal_email());
        setCity(persona.getCity());
        setActive(persona.getActive());
        setCreated_date(persona.getCreated_date());
        setImagen_url(persona.getImagen_url());
        setTermination_date(persona.getTermination_date());
        setAdmin(persona.getAdmin());
    }



}
