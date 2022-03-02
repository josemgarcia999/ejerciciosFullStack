package com.example.EJ31CRUD.Estudiante_Asignatura.application;

import com.example.EJ31CRUD.Estudiante_Asignatura.domain.EstudianteAsignaturaEntity;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.imput.EstudianteAsignaturaImputDTO;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output.EstudianteAsignaturaOutputDTO;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output.EstudianteAsignaturaOutputDTOList;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.repository.jpa.EstudianteAsignaturaRepo;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import com.example.EJ31CRUD.Student.infraestructure.repository.jpa.StudentRepo;
import com.example.EJ31CRUD.excepciones.NotFoundException;
import com.example.EJ31CRUD.excepciones.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EstudianteAsignaturaService implements iEstudianteAsignatura {

  @Autowired EstudianteAsignaturaRepo asignaturaRepo;

  @Autowired
  StudentRepo studentRepo;

  @Override
  public EstudianteAsignaturaOutputDTO addAsignatura(
      EstudianteAsignaturaImputDTO estudianteAsignaturaImputDTO) {
    EstudianteAsignaturaEntity asignatura =
        new EstudianteAsignaturaEntity(estudianteAsignaturaImputDTO);
    asignaturaRepo.saveAndFlush(asignatura);
    return new EstudianteAsignaturaOutputDTO(asignatura);
  }

  @Override
  public EstudianteAsignaturaOutputDTO findById(String id) throws Exception {
    EstudianteAsignaturaEntity asignatura =
        asignaturaRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe el ID"));
    return new EstudianteAsignaturaOutputDTO(asignatura);
  }

  @Override
  public EstudianteAsignaturaOutputDTOList getAll() {
    List<EstudianteAsignaturaEntity> asignaturasEntities = asignaturaRepo.findAll();
    List<EstudianteAsignaturaOutputDTO> asignaturas =
        asignaturasEntities.stream()
            .map(a -> new EstudianteAsignaturaOutputDTO(a))
            .collect(Collectors.toList());
    EstudianteAsignaturaOutputDTOList estudianteAsignaturaOutputDTOList= new EstudianteAsignaturaOutputDTOList();
    estudianteAsignaturaOutputDTOList.setEstudianteAsignaturaOutputDTOList(asignaturas);
    return estudianteAsignaturaOutputDTOList;
  }

  @Override
  public void deleteAsignatura(String id) throws Exception {

    EstudianteAsignaturaEntity asignatura = asignaturaRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe el ID"));
    if (asignatura.getEstudiantes().isEmpty()) {
      asignaturaRepo.delete(asignatura);
    }else throw new UnprocesableException("No se pueden borrar asignaturas que estan cursando los estudiantes");

  }

  @Override
  public EstudianteAsignaturaOutputDTO updateAsignatura(String id, EstudianteAsignaturaImputDTO estudianteAsignaturaImputDTO) throws Exception {
    EstudianteAsignaturaEntity asignatura = asignaturaRepo.findById(id).orElseThrow(() -> new NotFoundException ("No existe el ID"));
    if (estudianteAsignaturaImputDTO.getAsignatura() != null) {
      asignatura.setAsignatura(estudianteAsignaturaImputDTO.getAsignatura());
    }

    if (estudianteAsignaturaImputDTO.getComments() != null)
      asignatura.setComments(estudianteAsignaturaImputDTO.getComments());
    if(estudianteAsignaturaImputDTO.getInitialDate() != null)
      asignatura.setInitialDate(estudianteAsignaturaImputDTO.getInitialDate());
    if(estudianteAsignaturaImputDTO.getFinishDate()!=estudianteAsignaturaImputDTO.getFinishDate()){
      asignatura.setFinishDate(estudianteAsignaturaImputDTO.getFinishDate());
    }
    asignaturaRepo.saveAndFlush(asignatura);
    EstudianteAsignaturaOutputDTO estudianteAsignaturaOutputDTO = new EstudianteAsignaturaOutputDTO(asignatura);
    return estudianteAsignaturaOutputDTO;

  }
  @Override
  public List<EstudianteAsignaturaOutputDTO> mostrarAsignaturas(String id) throws Exception {
    StudentEntity estudiante = studentRepo.findById(id).orElseThrow(() -> new NotFoundException("No existe el ID"));
    List<EstudianteAsignaturaOutputDTO> listaAsignaturas = new ArrayList<>();
    for (int i = 0; i <estudiante.getAsignaturas().size(); i++) {
      EstudianteAsignaturaOutputDTO asignaturaOutputDTO = new EstudianteAsignaturaOutputDTO(estudiante.getAsignaturas().get(i));
      listaAsignaturas.add(asignaturaOutputDTO);
    }
    return listaAsignaturas;
  }

  boolean noContieneAsignatura(List<EstudianteAsignaturaEntity> asignaturas, String idAsignatura) {
    for (int i = 0; i < asignaturas.size(); i++) {
      if (asignaturas.get(i).getIdAsigntura().equalsIgnoreCase(idAsignatura)) return false;
    }
    return true;
  }

}
