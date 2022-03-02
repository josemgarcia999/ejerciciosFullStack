package com.example.EJ31CRUD.Student.application;

import com.example.EJ31CRUD.Student.infraestructure.controller.dto.imput.StudentImputDTO;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.output.StudentOutputDTO;
import com.example.EJ31CRUD.Student.infraestructure.controller.dto.output.StudentOutputDTOList;

import java.util.List;

public interface IStudent {
    public StudentOutputDTO addStudent(StudentImputDTO student) throws Exception;
    public StudentOutputDTO findStudentById(String id,String parametro) throws Exception;
    public StudentOutputDTOList getAll();
    public void deleteStudient(String id) throws Exception;
    public StudentOutputDTO updateStudent(String id, StudentImputDTO studentImputDTO) throws Exception;
    public StudentOutputDTO addAsignatura(String idEstudiante,List<String> listaAsignaturas) throws Exception;
    public StudentOutputDTO deleteAsignatura(String idEstudiante,List<String> listaAsignaturas) throws Exception;

}
