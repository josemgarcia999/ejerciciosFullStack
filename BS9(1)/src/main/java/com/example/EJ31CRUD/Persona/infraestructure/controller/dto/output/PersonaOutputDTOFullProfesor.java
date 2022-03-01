package com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output;

import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Profesor.domain.ProfesorEntity;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.output.StudentOutputDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PersonaOutputDTOFullProfesor extends PersonaOutputDTO{


    String idProfesor;
    String comments;
    String branch;
    List<StudentOutputDTO> alumnos;

    public PersonaOutputDTOFullProfesor(PersonaEntity p, ProfesorEntity pr){
        super(p);
        setIdProfesor(pr.getIdProfesor());
        setComments(pr.getComments());
        setBranch(pr.getBranch());
        if(pr.getEstudiantes() != null)
            setAlumnos(pr.getEstudiantes().stream().map(StudentOutputDTO::new).collect(Collectors.toList()));


    }

}
