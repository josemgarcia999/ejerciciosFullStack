package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa;

import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends JpaRepository<PersonaEntity,Integer> {

}
