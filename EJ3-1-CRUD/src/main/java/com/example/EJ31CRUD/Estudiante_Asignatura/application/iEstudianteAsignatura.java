package com.example.EJ31CRUD.Estudiante_Asignatura.application;

import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.imput.EstudianteAsignaturaImputDTO;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output.EstudianteAsignaturaOutputDTO;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output.EstudianteAsignaturaOutputDTOList;

import java.util.List;

public interface iEstudianteAsignatura {

    public EstudianteAsignaturaOutputDTO addAsignatura(EstudianteAsignaturaImputDTO estudianteAsignaturaImputDTO);
    public EstudianteAsignaturaOutputDTO findById(String id) throws Exception;
    public EstudianteAsignaturaOutputDTOList getAll();
    public void deleteAsignatura(String id) throws Exception;
    public EstudianteAsignaturaOutputDTO updateAsignatura(String id, EstudianteAsignaturaImputDTO estudianteAsignaturaImputDTO) throws Exception;
    public List<EstudianteAsignaturaOutputDTO> mostrarAsignaturas(String id) throws Exception;

}
