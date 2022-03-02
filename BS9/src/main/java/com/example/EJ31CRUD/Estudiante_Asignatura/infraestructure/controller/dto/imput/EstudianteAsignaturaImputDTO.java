package com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.imput;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EstudianteAsignaturaImputDTO {

    String asignatura;
    String comments;
    Date initialDate;
    Date finishDate;
    List<String> idEstudiantes;



}
