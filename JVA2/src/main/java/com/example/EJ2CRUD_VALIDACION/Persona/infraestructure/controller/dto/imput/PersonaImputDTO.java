package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


public record PersonaImputDTO (
    Integer id,
    String usuario,
    String password,
    String name,
    String surname,
    String company_email,
    String personal_email,
    String city,
    Boolean active,
    Date created_date,
    String imagen_url,
    Date termination_date
    )
{

    public void comprobarDTO() throws Exception {
        if (this.usuario() == null) throw new Exception("Usuario no puede ser nulo");

        if (this.usuario().length() < 6 || this.usuario().length() > 10)
            throw new Exception("Usuario debe tener entre 6 y 10 caracteres");

        if (this.password() == null) throw new Exception("Password no puede ser nulo");

        if (this.name() == null) throw new Exception("Name no puede ser nulo");

        if (this.surname() == null) throw new Exception("Surname no puede ser nulo");

        if (this.company_email() == null)
            throw new Exception("Company_email no puede ser nulo");

        if (this.personal_email() == null)
            throw new Exception("Personal_email no puede ser nulo");

        if (this.city() == null) throw new Exception("City no puede ser nulo");

        if (this.active() == null) throw new Exception("Active no puede ser nulo");

        if (this.created_date() == null) throw new Exception("Created_date no puede ser nulo");
    }

}
