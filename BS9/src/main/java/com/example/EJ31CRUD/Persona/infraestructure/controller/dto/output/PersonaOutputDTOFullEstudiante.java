package com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output;

import com.example.EJ31CRUD.Estudiante_Asignatura.domain.EstudianteAsignaturaEntity;
import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonaOutputDTOFullEstudiante extends PersonaOutputDTO {

  String idEstudiante;
  int numHoursWeek;
  String idProfesor;
  String branch;
  List<String> asignaturas;

  public PersonaOutputDTOFullEstudiante(PersonaEntity p, StudentEntity student) {
    super(p);
    setIdEstudiante(student.getId());
    if (student.getProfesor() != null) setIdProfesor(student.getProfesor().getIdProfesor());
    setNumHoursWeek(student.getNumHoursWeek());
    setBranch(student.getBranch());
    if (student.getAsignaturas() != null) {
      setAsignaturas(convertirListaEstudiantesaListaIds(student.getAsignaturas()));
    }
  }

  List<String> convertirListaEstudiantesaListaIds(List<EstudianteAsignaturaEntity> asignaturas) {
    List<String> idAsignaturas = new ArrayList<>();
    for (int i = 0; i < asignaturas.size(); i++) {
      idAsignaturas.add(asignaturas.get(i).getIdAsigntura());
    }
    return idAsignaturas;
  }
}
