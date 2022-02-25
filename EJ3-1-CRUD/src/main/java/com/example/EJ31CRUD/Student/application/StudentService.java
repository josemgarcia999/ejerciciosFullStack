package com.example.EJ31CRUD.Student.application;

import com.example.EJ31CRUD.Estudiante_Asignatura.domain.EstudianteAsignaturaEntity;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.repository.jpa.EstudianteAsignaturaRepo;
import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Persona.infraestructure.repository.jpa.PersonaRepo;
import com.example.EJ31CRUD.Profesor.domain.ProfesorEntity;
import com.example.EJ31CRUD.Profesor.infraestructure.repository.ProfesorRepo;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.imput.StudentImputDTO;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.output.StudentOutputDTO;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.output.StudentOutputDTOList;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.output.StudentOutputFullDTO;
import com.example.EJ31CRUD.Student.infraestructure.repository.jpa.StudentRepo;
import com.example.EJ31CRUD.excepciones.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudent {
  @Autowired StudentRepo studentRepo;

  @Autowired PersonaRepo personaRepo;

  @Autowired
  EstudianteAsignaturaRepo asignaturaRepo;

  @Autowired ProfesorRepo profesorRepo;

  @Override
  public StudentOutputDTO addStudent(StudentImputDTO studentDTO) throws Exception {
    PersonaEntity p = personaRepo.findById(Integer.parseInt(studentDTO.getIdPersona())).get();
    StudentEntity s = new StudentEntity(studentDTO);
    if (comprobarIDEstudiantes(studentRepo.findAll(), p.getId())
            && comprobarIDProfesores(profesorRepo.findAll(), p.getId())) {
      s.setPersona(p);
    } else throw new NotFoundException("ID en uso");


    if (studentDTO.getIdProfesor() != null) {
      ProfesorEntity pr = profesorRepo.findById(studentDTO.getIdProfesor()).get();
      s.setProfesor(pr);
    }


    studentRepo.saveAndFlush(s);
    StudentOutputDTO studentOutputDTO = new StudentOutputDTO(s);
    return studentOutputDTO;
  }

  @Override
  public StudentOutputDTO findStudentById(String id, String parametro) throws Exception {
    StudentEntity s = studentRepo.findById(id).orElseThrow(() -> new NotFoundException("Id no encontrado"));
    PersonaEntity pAsociada = personaRepo.findById(s.getPersona().getId()).orElseThrow(() -> new NotFoundException("Id no encontrado"));;
    if (parametro.equalsIgnoreCase("full")) {
      StudentOutputFullDTO studentOutputFullDTO = new StudentOutputFullDTO(s);
      return studentOutputFullDTO;
    } else {
      StudentOutputDTO studentOutputDTO = new StudentOutputDTO(s);
      return studentOutputDTO;
    }
  }

  @Override
  public StudentOutputDTOList getAll() {
    List<StudentEntity> studentEntities = studentRepo.findAll();
    List<StudentOutputDTO> students = new ArrayList<>();
    StudentOutputDTOList studentOutputDTOList = new StudentOutputDTOList();
    for (StudentEntity st : studentEntities) {
      StudentOutputDTO stu = new StudentOutputDTO(st);
      students.add(stu);
    }
    studentOutputDTOList.setStudentOutputDTOList(students);
    return studentOutputDTOList;
  }

  public void deleteStudient(String id) throws Exception {
    studentRepo.delete(
        studentRepo.findById(id).orElseThrow(() -> new NotFoundException("Id no encontrado")));
  }

  @Override
  public StudentOutputDTO updateStudent(String id, StudentImputDTO studentImputDTO)
      throws Exception {
    StudentEntity s = studentRepo.findById(id).orElseThrow(() -> new NotFoundException("Id no encontrado"));
    if (studentImputDTO.getIdPersona() != null) {

      PersonaEntity p =
          personaRepo
              .findById(Integer.valueOf(studentImputDTO.getIdPersona()))
              .orElseThrow(() -> new NotFoundException("Id no encontrado"));
      s.setPersona(p);
    }
    if (studentImputDTO.getIdProfesor() != null) {
      ProfesorEntity pr =
          profesorRepo
              .findById(studentImputDTO.getIdProfesor())
              .orElseThrow(() -> new NotFoundException("Id no encontrado"));
      s.setProfesor(pr);
    }

    if (studentImputDTO.getNumHoursWeek() != null) {
      s.setNumHoursWeek(studentImputDTO.getNumHoursWeek());
    }

    if (studentImputDTO.getBranch() != null) {
      s.setBranch(studentImputDTO.getBranch());
    }
    studentRepo.saveAndFlush(s);
    return new StudentOutputDTO(s);
  }

  @Override
  public StudentOutputDTO addAsignatura(String idEstudiante,List<String> listaAsignaturas) throws Exception {

    StudentEntity s = studentRepo.findById(idEstudiante).orElseThrow(()-> new NotFoundException("No es una asignatura valida"));
    for (int i = 0; i < listaAsignaturas.size(); i++) {
      EstudianteAsignaturaEntity as = asignaturaRepo.findById(listaAsignaturas.get(i)).orElseThrow(()-> new NotFoundException("No es una asignatura valida"));
      if(noContieneAsignatura(s.getAsignaturas(),listaAsignaturas.get(i)))
        s.getAsignaturas().add(as);
    }
    studentRepo.saveAndFlush(s);
    StudentOutputDTO studentOutputDTO = new StudentOutputDTO(s);
    return studentOutputDTO;
  }

  @Override
  public StudentOutputDTO deleteAsignatura(String idEstudiante, List<String> listaAsignaturas) throws Exception {
    StudentEntity s = studentRepo.findById(idEstudiante).orElseThrow(()-> new NotFoundException("No es un estudiante valido"));
    for (int i = 0; i < listaAsignaturas.size(); i++) {
      System.out.println(listaAsignaturas.get(i));
      EstudianteAsignaturaEntity as = asignaturaRepo.findById(listaAsignaturas.get(i)).orElseThrow(()-> new Exception("No es una asignatura valida"));
      if(contieneAsignatura(s.getAsignaturas(),listaAsignaturas.get(i)))
        s.getAsignaturas().remove(as);
    }
    studentRepo.saveAndFlush(s);
    StudentOutputDTO studentOutputDTO = new StudentOutputDTO(s);
    return studentOutputDTO;
  }


  boolean comprobarIDEstudiantes(List<StudentEntity> estudiantesCreados, Integer id) {
    for (int i = 0; i < estudiantesCreados.size(); i++) {
      if (estudiantesCreados.get(i).getPersona().getId() == id) return false;
    }
    return true;
  }

  boolean comprobarIDProfesores(List<ProfesorEntity> profesoresCreados, Integer id) {
    for (int i = 0; i < profesoresCreados.size(); i++) {
      if (profesoresCreados.get(i).getPersona().getId() == id) {
        return false;
      }
    }
    return true;
  }

  boolean noContieneAsignatura(List<EstudianteAsignaturaEntity> asignaturas, String idAsignatura){
    for (int i = 0; i< asignaturas.size();i++){
      if(asignaturas.get(i).getIdAsigntura().equalsIgnoreCase(idAsignatura))
        return false;
    }
    return true;
  }
  boolean contieneAsignatura(List<EstudianteAsignaturaEntity> asignaturas, String idAsignatura){
    for (int i = 0; i< asignaturas.size();i++){
      if(asignaturas.get(i).getIdAsigntura().equalsIgnoreCase(idAsignatura))
        return true;
    }
    return false;
  }

}
