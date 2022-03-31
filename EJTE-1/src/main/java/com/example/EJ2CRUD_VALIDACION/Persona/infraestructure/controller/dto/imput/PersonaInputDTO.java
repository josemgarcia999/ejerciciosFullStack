package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaInputDTO {

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
    }

}
