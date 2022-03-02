package com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output;

import com.example.EJ31CRUD.Estudiante_Asignatura.domain.EstudianteAsignaturaEntity;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class EstudianteAsignaturaOutputDTO {

  String idAsignatura;
  List<String> idEstudiantes;
  String asignatura;
  String comments;
  Date initialDate;
  Date finishDate;

  public EstudianteAsignaturaOutputDTO(EstudianteAsignaturaEntity asignatura) {
    if (asignatura == null) return;
    setIdAsignatura(asignatura.getIdAsigntura());
    if(asignatura.getEstudiantes() != null){
    setIdEstudiantes(convertirListaEstudiantesaListaIds(asignatura.getEstudiantes()));
    }

    setAsignatura(asignatura.getAsignatura());
    setComments(asignatura.getComments());
    setInitialDate(asignatura.getInitialDate());
    setFinishDate(asignatura.getFinishDate());
  }

  List<String> convertirListaEstudiantesaListaIds(List<StudentEntity> estudiantes) {
    List<String> idEstudiantes = new ArrayList<>();
    for (int i = 0; i < estudiantes.size(); i++) {
      idEstudiantes.add(estudiantes.get(i).getId());
    }
    return idEstudiantes;
  }
}
