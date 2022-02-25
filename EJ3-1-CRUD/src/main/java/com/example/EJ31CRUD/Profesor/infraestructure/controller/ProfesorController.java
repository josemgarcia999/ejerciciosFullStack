package com.example.EJ31CRUD.Profesor.infraestructure.controller;


import com.example.EJ31CRUD.Profesor.application.IProfesor;
import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.imput.ProfesorImputDTO;
import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.output.ProfesorOutputDTO;
import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.output.ProfesorOutputDTOList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profesor")
public class ProfesorController {

    @Autowired
    IProfesor profesorService;


    @PostMapping()
    public ProfesorOutputDTO addPersona(@RequestBody ProfesorImputDTO profesorDTO) throws Exception {
        return profesorService.addProfesor(profesorDTO);
    }

    @GetMapping("{id}")
    public ProfesorOutputDTO findProfesorById(@PathVariable String id) throws Exception {
        return profesorService.findProfesoyrById(id);
    }

    @GetMapping
    public ProfesorOutputDTOList getAll(){
        return profesorService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteProfesor(@PathVariable String id) throws Exception {
        profesorService.deleteProfesor(id);
    }

    @PutMapping("{id}")
    public ProfesorOutputDTO updateProfesor(@PathVariable String id,@RequestBody ProfesorImputDTO profesorImputDTO) throws Exception {
        return profesorService.updateProfesor(id,profesorImputDTO);

    }


}
