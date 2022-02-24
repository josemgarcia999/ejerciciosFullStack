package com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.repository.jpa;

import com.example.EJ31CRUD.Estudiante_Asignatura.domain.EstudianteAsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteAsignaturaRepo extends JpaRepository<EstudianteAsignaturaEntity,String> {
}
