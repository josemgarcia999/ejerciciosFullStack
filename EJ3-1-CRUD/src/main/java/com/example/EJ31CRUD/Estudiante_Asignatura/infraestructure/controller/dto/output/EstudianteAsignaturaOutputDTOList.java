package com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class EstudianteAsignaturaOutputDTOList {
    private List<EstudianteAsignaturaOutputDTO> estudianteAsignaturaOutputDTOList;
}
