package com.example.EJ31CRUD.Student.infraestructure.controller.dto.output;

import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class StudentOutputFullDTO extends StudentOutputDTO{



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

    public StudentOutputFullDTO (StudentEntity student){
        super(student);
        setUsuario(student.getPersona().getUsuario());
        setPassword(student.getPersona().getPassword());
        setName(student.getPersona().getName());
        setSurname(student.getPersona().getSurname());
        setCompany_email(student.getPersona().getCompany_email());
        setPersonal_email(student.getPersona().getPersonal_email());
        setCity(student.getPersona().getCity());
        setActive(student.getPersona().getActive());
        setCreated_date(student.getPersona().getCreated_date());
        setImagen_url(student.getPersona().getImagen_url());
        setTermination_date(student.getPersona().getTermination_date());

    }


}
