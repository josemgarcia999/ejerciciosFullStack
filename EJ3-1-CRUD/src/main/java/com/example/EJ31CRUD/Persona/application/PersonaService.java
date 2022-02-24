package com.example.EJ31CRUD.Persona.application;

import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output.PersonaOutputDTOFullEstudiante;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output.PersonaOutputDTOFullProfesor;
import com.example.EJ31CRUD.Persona.infraestructure.repository.jpa.PersonaRepo;
import com.example.EJ31CRUD.Profesor.domain.ProfesorEntity;
import com.example.EJ31CRUD.Profesor.infraestructure.repository.ProfesorRepo;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import com.example.EJ31CRUD.Student.infraestructure.repository.jpa.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersona {

  @Autowired PersonaRepo personaRepo;

  @Autowired StudentRepo studentRepo;

  @Autowired ProfesorRepo profesorRepo;

  @Override
  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception {
    persona.comprobarDTO();
    PersonaEntity p = new PersonaEntity(persona);
    personaRepo.save(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public PersonaOutputDTO findById(Integer id, String parametro) throws Exception {
    PersonaEntity p = personaRepo.findById(id).orElseThrow(() -> new Exception("ID no encontrado"));
    if (parametro.equalsIgnoreCase("full")) {
      List<StudentEntity> estudiantes = studentRepo.findAll();
      for (int i = 0; i < estudiantes.size(); i++) {
        if (estudiantes.get(i).getPersona().getId().equals(p.getId())) {
          return new PersonaOutputDTOFullEstudiante(p, estudiantes.get(i));
        }
      }

      List<ProfesorEntity> profesores = profesorRepo.findAll();
      for (int i = 0; i < profesores.size(); i++) {
        if (profesores.get(i).getPersona().getId().equals(p.getId())) {
          return new PersonaOutputDTOFullProfesor(p, profesores.get(i));
        }
      }

      PersonaOutputDTO personaOutputDto = new PersonaOutputDTO(p);
      return personaOutputDto;
    } else {
      PersonaOutputDTO personaOutputDto = new PersonaOutputDTO(p);
      return personaOutputDto;
    }
  }

  @Override
  public List<PersonaOutputDTO> getAllPersonas(String parametro) {
    List<PersonaEntity> personasEntities = personaRepo.findAll();
    List<StudentEntity> estudiantes = studentRepo.findAll();
    List<ProfesorEntity> profesores = profesorRepo.findAll();
    List<PersonaOutputDTO> personaOutputDTOS = new ArrayList<>();
    for (PersonaEntity pe : personasEntities) {
      if (parametro.equalsIgnoreCase("full")) {
        if (comprobarIDEstudiantes(estudiantes, pe.getId())) {
          String idEstudiante = devolverIDEstudiante(pe.getId(), estudiantes);
          System.out.println("Soy estudiante y mi ID es " + idEstudiante);

          PersonaOutputDTOFullEstudiante persona1 =
              new PersonaOutputDTOFullEstudiante(pe, studentRepo.findById(idEstudiante).get());
          personaOutputDTOS.add(persona1);
        } else {
          if (comprobarIDProfesores(profesores, pe.getId())) {
            String idProfesor = devolverIDProfesor(pe.getId(), profesores);
            System.out.println("Soy profesor y mi ID es " + idProfesor);
            PersonaOutputDTOFullProfesor persona2 =
                new PersonaOutputDTOFullProfesor(pe, profesorRepo.findById(idProfesor).get());
            personaOutputDTOS.add(persona2);
          } else {
            PersonaOutputDTO persona3 = new PersonaOutputDTO(pe);
            personaOutputDTOS.add(persona3);
          }
        }

      } else {
        List<PersonaOutputDTO> personas =
            personasEntities.stream()
                .map(person -> new PersonaOutputDTO(person))
                .collect(Collectors.toList());
        return personas;
      }
    }
    return personaOutputDTOS;
  }

  @Override
  public List<PersonaOutputDTO> findByUsuario(String usuario, String parametro) {
    List<PersonaEntity> personas = personaRepo.findAll();
    List<StudentEntity> estudiantes = studentRepo.findAll();
    List<ProfesorEntity> profesores = profesorRepo.findAll();
    List<PersonaOutputDTO> personaOutputDTOS = new ArrayList<>();
    for (PersonaEntity p : personas) {
      if (p.getUsuario().equalsIgnoreCase(usuario)) {
        if (parametro.equalsIgnoreCase("full")) {
          System.out.println("Estoy dentro de full y el id de la persona es " + p.getId());
          if (comprobarIDEstudiantes(estudiantes, p.getId())) {
            String idEstudiante = devolverIDEstudiante(p.getId(), estudiantes);
            System.out.println("Soy estudiante y mi ID es " + idEstudiante);

            PersonaOutputDTOFullEstudiante persona1 =
                new PersonaOutputDTOFullEstudiante(p, studentRepo.findById(idEstudiante).get());
            personaOutputDTOS.add(persona1);
          } else {
            if (comprobarIDProfesores(profesores, p.getId())) {
              String idProfesor = devolverIDProfesor(p.getId(), profesores);
              System.out.println("Soy profesor y mi ID es " + idProfesor);
              PersonaOutputDTOFullProfesor persona2 =
                  new PersonaOutputDTOFullProfesor(p, profesorRepo.findById(idProfesor).get());
              personaOutputDTOS.add(persona2);
            } else {
              PersonaOutputDTO persona3 = new PersonaOutputDTO(p);
              personaOutputDTOS.add(persona3);
            }
          }
        } else {
          PersonaOutputDTO persona = new PersonaOutputDTO(p);
          personaOutputDTOS.add(persona);
        }
      }
    }
    return personaOutputDTOS;
  }

  @Override
  public void deletePersona(Integer id) throws Exception {
    List<StudentEntity> estudiantes = studentRepo.findAll();
    List<ProfesorEntity> profesores = profesorRepo.findAll();
    if (comprobarIDEstudiantes(estudiantes, id) && comprobarIDProfesores(profesores, id)) {
      personaRepo.delete(
          personaRepo.findById(id).orElseThrow(() -> new Exception("Id no encontrado")));
    } else
      throw new Exception("No se puede borrar una persona asignada a un estudiante o profesor");
  }

  @Override
  public PersonaOutputDTO updatePersona(Integer id, PersonaImputDTO personaImputDTO)
      throws Exception {

    PersonaEntity p = personaRepo.findById(id).orElseThrow(() -> new Exception("Id no encontrado"));
    if (personaImputDTO.getUsuario().length() < 6 || personaImputDTO.getUsuario().length() > 10) {
      p.setUsuario(personaImputDTO.getUsuario());
    }

    if (personaImputDTO.getPassword() != null) {
      p.setPassword(personaImputDTO.getPassword());
    }
    if (personaImputDTO.getName() != null) {
      p.setName(personaImputDTO.getName());
    }
    if (personaImputDTO.getSurname() != null) {
      p.setSurname(personaImputDTO.getSurname());
    }
    if (personaImputDTO.getCompany_email() != null) {
      p.setCompany_email(personaImputDTO.getCompany_email());
    }
    if (personaImputDTO.getPersonal_email() != null) {
      p.setPersonal_email(personaImputDTO.getPersonal_email());
    }
    if (personaImputDTO.getCity() != null) {
      p.setCity(personaImputDTO.getCity());
    }
    if (personaImputDTO.getActive() != null) {
      p.setActive(personaImputDTO.getActive());
    }
    if (personaImputDTO.getImagen_url() != null) {
      p.setImagen_url(personaImputDTO.getImagen_url());
    }

    if (personaImputDTO.getTermination_date() != null) {
      p.setTermination_date(personaImputDTO.getTermination_date());
    }

    personaRepo.saveAndFlush(p);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  boolean comprobarIDEstudiantes(List<StudentEntity> estudiantesCreados, Integer id) {
    for (int i = 0; i < estudiantesCreados.size(); i++) {
      if (estudiantesCreados.get(i).getPersona().getId() == id) return true;
    }
    return false;
  }

  String devolverIDEstudiante(Integer idPersona, List<StudentEntity> estudiantes) {
    for (int i = 0; i < estudiantes.size(); i++) {
      if (estudiantes.get(i).getPersona().getId().equals(idPersona))
        return estudiantes.get(i).getId();
    }
    return null;
  }

  String devolverIDProfesor(Integer idPersona, List<ProfesorEntity> profesores) {
    for (int i = 0; i < profesores.size(); i++) {
      if (profesores.get(i).getPersona().getId().equals(idPersona))
        return profesores.get(i).getIdProfesor();
    }
    return null;
  }

  boolean comprobarIDProfesores(List<ProfesorEntity> profesoresCreados, Integer id) {
    for (int i = 0; i < profesoresCreados.size(); i++) {
      if (profesoresCreados.get(i).getPersona().getId() == id) {
        return true;
      }
    }
    return false;
  }
}
