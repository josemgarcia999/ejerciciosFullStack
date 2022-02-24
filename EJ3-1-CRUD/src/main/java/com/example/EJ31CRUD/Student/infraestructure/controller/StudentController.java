package com.example.EJ31CRUD.Student.infraestructure.controller;

import com.example.EJ31CRUD.Student.application.IStudent;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.imput.StudentImputDTO;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.output.StudentOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

  @Autowired IStudent studentService;

  @PostMapping()
  public StudentOutputDTO addPersona(@RequestBody StudentImputDTO studentDTO) throws Exception {
    return studentService.addStudent(studentDTO);
  }

  @GetMapping("{id}")
  public StudentOutputDTO findEstudianteById(
      @PathVariable String id,
      @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
          String outputType) throws Exception {
    return studentService.findStudentById(id, outputType);
  }

  @GetMapping
  public List<StudentOutputDTO> getAll() {
    return studentService.getAll();
  }

  @DeleteMapping("{id}")
  public void deleteStudent(@PathVariable String id) throws Exception {
    studentService.deleteStudient(id);
  }

  @PutMapping("{id}")
  public StudentOutputDTO updateStudent(@PathVariable String id, @RequestBody StudentImputDTO studentImputDTO) throws Exception {
    return studentService.updateStudent(id,studentImputDTO);
  }


  @PutMapping("/prueba/{id}")
  public void addAsignaturas(@PathVariable String id, @RequestBody List<String> listaAsignaturas) throws Exception {
    studentService.addAsignatura(id,listaAsignaturas);

  }

  @PutMapping("borrarAsignaturas/{id}")
  public void borrarAsignaturas(@PathVariable String id, @RequestBody List<String> listaAsignaturas) throws Exception {
    studentService.deleteAsignatura(id,listaAsignaturas);

  }



}
