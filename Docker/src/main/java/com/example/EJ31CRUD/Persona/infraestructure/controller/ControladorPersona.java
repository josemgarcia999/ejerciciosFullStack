package com.example.EJ31CRUD.Persona.infraestructure.controller;

import com.example.EJ31CRUD.Persona.application.IPersona;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ31CRUD.Persona.infraestructure.controller.dto.output.PersonaOutputDTOList;
import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.output.ProfesorOutputDTO;
import com.example.EJ31CRUD.configuration.IFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("persona")
public class ControladorPersona {

  @Autowired
  IPersona personaService;

  @Autowired
  IFeign iFeign;


  @PostMapping()
  public PersonaOutputDTO addPersona(@RequestBody PersonaImputDTO personaDTO) throws Exception {
    return personaService.añadirUsuario(personaDTO);
  }

  @GetMapping("{id}")
  public PersonaOutputDTO findById(@PathVariable Integer id, @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
          String outputType) throws Exception {
    return personaService.findById(id,outputType);
  }

  @GetMapping()
  public PersonaOutputDTOList getAll(
          @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
                  String outputType) {
    return personaService.getAllPersonas(outputType);
  }

  @GetMapping("{usuario}/usuario")
  public PersonaOutputDTOList buscarPersonaUsuario(@PathVariable String usuario, @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
          String outputType) {
    return personaService.findByUsuario(usuario,outputType);
  }

  @DeleteMapping("{id}")
  public void borrarPersona(@PathVariable Integer id) throws Exception {
    personaService.deletePersona(id);
  }

  @PutMapping("update/{id}")
  public PersonaOutputDTO actualizarUsuario(@PathVariable Integer id, @RequestBody PersonaImputDTO personaImputDTO) throws Exception {
    return personaService.updatePersona(id,personaImputDTO);
  }
  @GetMapping("/profesor/{id}")

  ResponseEntity<ProfesorOutputDTO> getProfesorRestTemplate(@PathVariable String id){
    ResponseEntity<ProfesorOutputDTO> rs = new RestTemplate().getForEntity("http://localhost:8081/profesor/"+id,ProfesorOutputDTO.class);
    return ResponseEntity.ok(rs.getBody());

  }
    @GetMapping("/feing/{id}")
    ResponseEntity<ProfesorOutputDTO> getProfesorFeign(@PathVariable String id){
        ResponseEntity<ProfesorOutputDTO> respuesta = iFeign.callServer(id);
        return respuesta;
    }

}


