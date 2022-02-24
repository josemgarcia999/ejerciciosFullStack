package com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.output;


import com.example.EJ31CRUD.Profesor.domain.ProfesorEntity;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfesorOutputDTO {

    String idProfesor;
    String idPersona;
    String comments;
    String branch;
    List<String> alumnos;


    public ProfesorOutputDTO (ProfesorEntity profesorEntity){
        if(profesorEntity == null)
            return;
        setIdProfesor(profesorEntity.getIdProfesor());
        setIdPersona(String.valueOf(profesorEntity.getPersona().getId()));
        setComments(profesorEntity.getComments());
        setBranch(profesorEntity.getBranch());
        if(profesorEntity.getEstudiantes() != null){
            setAlumnos(convertirListaEstudiantesaListaIds(profesorEntity.getEstudiantes()));
        }
    }


    List<String> convertirListaEstudiantesaListaIds(List<StudentEntity> alumnos) {
        List<String> idAlumnos = new ArrayList<>();
        for (int i = 0; i < alumnos.size(); i++) {
            idAlumnos.add(alumnos.get(i).getId());
        }
        return idAlumnos;
    }


}
