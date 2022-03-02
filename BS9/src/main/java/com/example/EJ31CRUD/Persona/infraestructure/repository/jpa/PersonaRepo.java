package com.example.EJ31CRUD.Persona.infraestructure.repository.jpa;

import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends JpaRepository<PersonaEntity,Integer> {

}
