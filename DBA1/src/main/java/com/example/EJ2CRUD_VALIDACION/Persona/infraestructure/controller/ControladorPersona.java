package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller;

import com.example.EJ2CRUD_VALIDACION.Persona.application.IPersona;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("persona")
public class ControladorPersona {

  @Autowired IPersona personaService;

  @Autowired PersonaRepository personaRepo;

  @Autowired EntityManager em;

  public static final String GREATER_THAN = "greater";
  public static final String LESS_THAN = "less";
  public static final String EQUAL = "equal";

  @PostMapping()
  public PersonaOutputDTO addPersona(@RequestBody PersonaImputDTO personaDTO) throws Exception {
    return personaService.añadirUsuario(personaDTO);
  }

  @GetMapping("{id}")
  public PersonaOutputDTO findById(@PathVariable Integer id) throws Exception {

    return personaService.findById(id);
  }

  @GetMapping()
  public List<PersonaOutputDTO> getAll() {
    return personaService.getAllPersonas();
  }

  @GetMapping("{usuario}/usuario")
  public List<PersonaOutputDTO> buscarPersonaUsuario(@PathVariable String usuario) {
    return personaService.findByUsuario(usuario);
  }

  @DeleteMapping("{id}")
  public void borrarPersona(@PathVariable Integer id) throws Exception {
    personaService.deletePersona(id);
  }

  @PutMapping("update/{id}")
  public PersonaOutputDTO actualizarUsuario(
      @PathVariable Integer id, @RequestBody PersonaImputDTO personaImputDTO) throws Exception {
    return personaService.updatePersona(id, personaImputDTO);
  }

  @GetMapping("/get")
  public List<PersonaOutputDTO> getData(
      @RequestParam(required = false, name = "usuario") String usuario,
      @RequestParam(required = false, name = "name") String name,
      @RequestParam(required = false, name = "surname") String surname,
      @RequestParam(required = false, name = "created_date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date created_date,
      @RequestParam(required = false, name = "dateCondition") String dateCondition,
      @RequestParam(required = false, name = "ordenar" )String ordenar)
  {


    HashMap<String, Object> data = new HashMap<>();

    if (usuario != null) data.put("usuario", usuario);
    if (name != null) data.put("name", name);
    if (surname != null) data.put("surname", surname);
    if (dateCondition == null) dateCondition = GREATER_THAN;
    if (!dateCondition.equals(GREATER_THAN)
        && !dateCondition.equals(LESS_THAN)
        && !dateCondition.equals(EQUAL)) dateCondition = GREATER_THAN;
    if (created_date != null) {
      data.put("created_date", created_date);
      data.put("dateCondition", dateCondition);
    }
    if(ordenar != null){
      data.put("ordenar",ordenar);
    }


    List<PersonaEntity> personas = personaRepo.getData(data);
    List<PersonaOutputDTO> personasOutputDto = new ArrayList<>();

    for (PersonaEntity persona : personas) {
      personasOutputDto.add(new PersonaOutputDTO(persona));
    }

    return personasOutputDto;
  }
}
