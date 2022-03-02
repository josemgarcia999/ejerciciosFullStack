package com.example.EJ31CRUD.configuration;

import com.example.EJ31CRUD.Estudiante_Asignatura.domain.EstudianteAsignaturaEntity;
import com.example.EJ31CRUD.Profesor.domain.ProfesorEntity;
import com.example.EJ31CRUD.Student.domain.StudentEntity;

import java.util.List;

public class Utiles {

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
}
