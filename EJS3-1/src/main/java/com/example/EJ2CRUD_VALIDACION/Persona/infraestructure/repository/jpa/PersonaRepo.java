package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa;

import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepo extends JpaRepository<PersonaEntity,Integer> {
    public List<PersonaEntity> findByUsuario(String usuario);
}
