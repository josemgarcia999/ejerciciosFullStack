package com.example.EJ31CRUD.Profesor.application;

import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Persona.infraestructure.repository.jpa.PersonaRepo;
import com.example.EJ31CRUD.Profesor.domain.ProfesorEntity;
import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.imput.ProfesorImputDTO;
import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.output.ProfesorOutputDTO;
import com.example.EJ31CRUD.Profesor.infraestructure.repository.ProfesorRepo;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import com.example.EJ31CRUD.Student.infraestructure.repository.jpa.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorService implements IProfesor {

  @Autowired PersonaRepo personaRepo;


  @Autowired
  StudentRepo studentRepo;

  @Autowired ProfesorRepo profesorRepo;

  @Override
  public ProfesorOutputDTO addProfesor(ProfesorImputDTO profesor) throws Exception {
    PersonaEntity persona = personaRepo.findById(Integer.parseInt(profesor.getIdPersona())).get();
    ProfesorEntity pr = new ProfesorEntity(profesor);
    if(comprobarIDEstudiantes(studentRepo.findAll(),persona.getId())&&comprobarIDProfesores(profesorRepo.findAll(),persona.getId())){
      pr.setPersona(persona);
    } else throw new Exception("ID en uso");
    profesorRepo.saveAndFlush(pr);
    ProfesorOutputDTO profe = new ProfesorOutputDTO(pr);
    return profe;
  }

  @Override
  public ProfesorOutputDTO findProfesoyrById(String id) throws Exception {
    ProfesorEntity pr =
        profesorRepo.findById(id).orElseThrow(() -> new Exception("Id no encontrado"));
    ProfesorOutputDTO prof = new ProfesorOutputDTO(pr);
    return prof;
  }

  @Override
  public List<ProfesorOutputDTO> getAll() {
    List<ProfesorEntity> profesorEntities = profesorRepo.findAll();
    List<ProfesorOutputDTO> profesores = new ArrayList<>();
    for (ProfesorEntity pr : profesorEntities) {
      ProfesorOutputDTO prof = new ProfesorOutputDTO(pr);
      profesores.add(prof);
    }
    return profesores;
  }

  @Override
  public void deleteProfesor(String id) throws Exception {
    profesorRepo.findById(id).orElseThrow(() -> new Exception("Id no encontrado"));
  }

  @Override
  public ProfesorOutputDTO updateProfesor(String id, ProfesorImputDTO profesorImputDTO) throws Exception {
  ProfesorEntity pr = profesorRepo.findById(id).orElseThrow(() -> new Exception("Id no encontrado"));
  if(profesorImputDTO.getIdProfesor()!= null){
    PersonaEntity p =
            personaRepo
                    .findById(Integer.valueOf(profesorImputDTO.getIdPersona()))
                    .orElseThrow(() -> new Exception("Id no encontrado"));
    pr.setPersona(p);

  }
  if(profesorImputDTO.getComments()!=null){
    pr.setComments(profesorImputDTO.getComments());
  }
  if(profesorImputDTO.getBranch()!=null){
    pr.setBranch(profesorImputDTO.getBranch());
  }

  profesorRepo.saveAndFlush(pr);
  return new ProfesorOutputDTO(pr);

  }
  boolean comprobarIDEstudiantes(List<StudentEntity> estudiantesCreados, Integer id){
    for (int i = 0; i <estudiantesCreados.size(); i++) {
      if(estudiantesCreados.get(i).getPersona().getId()==id)
        return false;
    }
    return true;

  }
  boolean comprobarIDProfesores(List<ProfesorEntity> profesoresCreados, Integer id){
    for (int i = 0; i <profesoresCreados.size(); i++) {
      if(profesoresCreados.get(i).getPersona().getId() == id) {
        return false;
      }
    }
    return true;
  }

}
