package com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller;

import com.example.EJ31CRUD.Estudiante_Asignatura.application.iEstudianteAsignatura;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.imput.EstudianteAsignaturaImputDTO;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output.EstudianteAsignaturaOutputDTO;
import com.example.EJ31CRUD.Estudiante_Asignatura.infraestructure.controller.dto.output.EstudianteAsignaturaOutputDTOList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asignatura")
public class EstudianteAsignaturaController {

    @Autowired
    iEstudianteAsignatura asignaturaService;

    @GetMapping()
    public EstudianteAsignaturaOutputDTOList mostrarTodasAsignaturas(){
        return asignaturaService.getAll();
    }

    @GetMapping("{id}")
    public EstudianteAsignaturaOutputDTO findbyId(@PathVariable String id) throws Exception {
        return asignaturaService.findById(id);
    }

    @GetMapping("{id}/asignaturas")
    public List<EstudianteAsignaturaOutputDTO> mostrarAsignaturasEstudiante(@PathVariable String id) throws Exception {
        return asignaturaService.mostrarAsignaturas(id);

    }


    @PostMapping()
    public EstudianteAsignaturaOutputDTO addAsignatura(@RequestBody EstudianteAsignaturaImputDTO estudianteAsignaturaImputDTO){
        return asignaturaService.addAsignatura(estudianteAsignaturaImputDTO);
    }

    @DeleteMapping("{id}")
    public void deleteAsignatura(@PathVariable String id) throws Exception {
        asignaturaService.deleteAsignatura(id);
    }

    @PutMapping("{id}")
    public EstudianteAsignaturaOutputDTO updateAsignatura(@PathVariable String id, @RequestBody EstudianteAsignaturaImputDTO estudianteAsignaturaImputDTO) throws Exception {
        return asignaturaService.updateAsignatura(id,estudianteAsignaturaImputDTO);

    }




}
