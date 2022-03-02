package com.example.EJ31CRUD.Student.domain;

import com.example.EJ31CRUD.Estudiante_Asignatura.domain.EstudianteAsignaturaEntity;
import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Profesor.domain.ProfesorEntity;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.imput.StudentImputDTO;
import com.example.EJ31CRUD.configuration.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Student")
@NoArgsConstructor
public class StudentEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
  @GenericGenerator(
      name = "student_seq",
      strategy = "com.example.EJ31CRUD.configuration.StringPrefixedSequenceIdGenerator",
      parameters = {
        @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
        @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU_"),
        @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
      })
  @Column(name = "id")
  String id;

  @OneToOne(fetch = FetchType.LAZY)
  PersonaEntity persona;

  @ManyToOne
  @JoinColumn(name = "idProfesor")
  ProfesorEntity profesor;

  @NonNull
  int numHoursWeek;

  String comments;

  @NonNull
  String branch;


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "estudiante_asignaturas",
          joinColumns = {@JoinColumn(name = "id")},
          inverseJoinColumns = {@JoinColumn(name = "idAsignatura")}
  )
  List<EstudianteAsignaturaEntity> asignaturas;

  public StudentEntity(StudentImputDTO studentImputDTO){
    if (studentImputDTO == null) return;
    setNumHoursWeek(studentImputDTO.getNumHoursWeek());
    setBranch(studentImputDTO.getBranch());
    setComments(studentImputDTO.getComments());
  }
}