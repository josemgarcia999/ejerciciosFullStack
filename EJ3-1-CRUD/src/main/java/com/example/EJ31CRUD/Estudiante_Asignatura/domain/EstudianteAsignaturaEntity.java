package com.example.EJ31CRUD.Estudiante_Asignatura.domain;

import com.example.EJ31CRUD.Estudiante_Asignatura.application.EstudianteAsignaturaService;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.imput.EstudianteAsignaturaImputDTO;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import com.example.EJ31CRUD.configuration.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class EstudianteAsignaturaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asignatura_seq")
  @GenericGenerator(
      name = "asignatura_seq",
      strategy = "com.example.EJ31CRUD.configuration.StringPrefixedSequenceIdGenerator",
      parameters = {
        @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ASG_"),
        @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
      })
  @Column(name = "id")
  String idAsigntura;

  @ManyToMany(mappedBy = "asignaturas")
  List<StudentEntity> estudiantes;

  String asignatura;
  String comments;
  Date initialDate;
  Date finishDate;



  public EstudianteAsignaturaEntity(EstudianteAsignaturaImputDTO estudianteAsignaturaImputDTO) {
    if (estudianteAsignaturaImputDTO == null) return;
    setAsignatura(estudianteAsignaturaImputDTO.getAsignatura());
    setComments(estudianteAsignaturaImputDTO.getComments());
    setInitialDate(estudianteAsignaturaImputDTO.getInitialDate());
    setFinishDate(estudianteAsignaturaImputDTO.getFinishDate());
  }
}
