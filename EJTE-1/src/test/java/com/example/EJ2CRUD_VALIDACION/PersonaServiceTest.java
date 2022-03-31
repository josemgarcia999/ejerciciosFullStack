package com.example.EJ2CRUD_VALIDACION;

import com.example.EJ2CRUD_VALIDACION.Persona.application.PersonaService;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaInputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa.PersonaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonaServiceTest {

  @InjectMocks PersonaService personaService;

  @Mock PersonaRepo personaRepo;

  PersonaEntity p1, p2;




  @BeforeAll
  void init() {
    Date date1 = new Date();
    Date date2 = new Date();
    p1 =
        new PersonaEntity(
            "usuario123",
            "password",
            "Jose Manuel",
            "Garcia Ronda",
            "josemanuel@gmail.com",
            "prueba@gmail.com",
            "Ubeda",
            true,
            date1,
            "url",
            date2);
    p1.setId(1);
    p2 =
        new PersonaEntity(
            "usuario123",
            "password",
            "Juanmy",
            "Villacreces",
            "juanmy@gmail.com",
            "prueba@gmail.com",
            "Cambil",
            true,
            date1,
            "url",
            date2);
    p2.setId(2);


  }

  @Test
  void añadirPersonaTest() throws Exception {
    PersonaInputDTO personaInputDTO = new PersonaInputDTO();
    crearPersonaInputDTO(personaInputDTO);
    PersonaEntity personaGuardar = new PersonaEntity(personaInputDTO);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(personaGuardar);
    personaOutputDTO.setId(1);
    when(personaRepo.save(ArgumentMatchers.any(PersonaEntity.class))).thenReturn(personaGuardar);
    PersonaOutputDTO pcomprueba = personaService.añadirUsuario(personaInputDTO);
    comprobarUsuario(personaGuardar, pcomprueba);
  }

  @Test
  void getAllPersonasTest() {
    List<PersonaEntity> personas = new ArrayList();
    List<PersonaOutputDTO> listaInicialDTO = new ArrayList();
    personas.add(p1);
    listaInicialDTO.add(new PersonaOutputDTO(p1));
    given(personaRepo.findAll()).willReturn(personas);
    List<PersonaOutputDTO> listaComprobar = personaService.getAllPersonas();
    Assertions.assertEquals(listaComprobar.size(), listaInicialDTO.size());
    verify(personaRepo).findAll();

  }

  @Test
  void findByIdTest() throws Exception {
    PersonaInputDTO personaInputDTO = new PersonaInputDTO();
    crearPersonaInputDTO(personaInputDTO);
    PersonaEntity personaGuardar = new PersonaEntity(personaInputDTO);
    personaGuardar.setId(1);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(personaGuardar);
    personaOutputDTO.setId(1);
    given(personaRepo.findById(1)).willReturn(Optional.of(personaGuardar));
    PersonaOutputDTO pcomprueba = personaService.findById(1);
    comprobarUsuario(personaGuardar, pcomprueba);

  }

  @Test
  public void updatePersonaTest() throws Exception {
    PersonaInputDTO personaInputDTO = new PersonaInputDTO();
    crearPersonaInputDTO(personaInputDTO);
    personaInputDTO.setName("Probando");
    p1.setName("Probando");
    given(personaRepo.findById(p1.getId())).willReturn(Optional.of(p1));
    PersonaOutputDTO p = personaService.updatePersona(p1.getId(),personaInputDTO);
    Assertions.assertEquals(p.getName(),p1.getName());
  }
  @Test
  void deleteByIdTest(){
    List<PersonaEntity> personas = new ArrayList();
    personas.add(p1);
    personas.add(p2);
    personas.remove(p1);
    given(personaRepo.findById(1)).willReturn(Optional.ofNullable(buscar(personas, 1)));
    PersonaEntity pcomprueba= personaRepo.findById(1).orElse(null);
    Assertions.assertNull(pcomprueba);

  }



  public void crearPersonaInputDTO(PersonaInputDTO persona) {
    Date date1 = new Date();
    Date date2 = new Date();
    persona.setUsuario("usuario123");
    persona.setPassword("123456");
    persona.setName("Jose Manuel");
    persona.setSurname("Garcia Ronda");
    persona.setCity("Ubeda");
    persona.setActive(true);
    persona.setCompany_email("joseubeda@gmail.com");
    persona.setPersonal_email("joseubeda2@gmail.com");
    persona.setCreated_date(date1);
    persona.setTermination_date(date2);
    persona.setImagen_url("urlImagen1");
  }

  public void comprobarUsuario(PersonaEntity persona, PersonaOutputDTO personaOutputDTO) {
    Assertions.assertEquals(personaOutputDTO.getUsuario(), persona.getUsuario());
    Assertions.assertEquals(personaOutputDTO.getPassword(), persona.getPassword());
    Assertions.assertEquals(personaOutputDTO.getName(), persona.getName());
    Assertions.assertEquals(personaOutputDTO.getCity(), persona.getCity());
    Assertions.assertEquals(personaOutputDTO.getActive(), persona.getActive());
    Assertions.assertEquals(personaOutputDTO.getCompany_email(), persona.getCompany_email());
    Assertions.assertEquals(personaOutputDTO.getPersonal_email(), persona.getPersonal_email());
    Assertions.assertEquals(personaOutputDTO.getCreated_date(), persona.getCreated_date());
    Assertions.assertEquals(personaOutputDTO.getTermination_date(), persona.getTermination_date());
    Assertions.assertEquals(personaOutputDTO.getImagen_url(), persona.getImagen_url());
  }

  PersonaEntity buscar(List<PersonaEntity> personas,Integer id){
    for (int i  = 0; i < personas.size();i++){
        if(personas.get(i).getId() == id)
          return personas.get(i);
    }
    return null;
  }

  PersonaEntity borrarTodo(List<PersonaEntity> personas){
    for (int i = 0; i< personas.size();i++){
      personas.remove(i);
    }
    return null;
  }
}
