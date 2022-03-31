package com.example.EJ2CRUD_VALIDACION;

import com.example.EJ2CRUD_VALIDACION.Persona.application.PersonaService;
import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaInputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa.PersonaRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.*;
import java.util.logging.Logger;
import java.util.logging.Logger;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePersonaControllerTest {

  @Autowired MockMvc mockMvc;

  @MockBean PersonaRepo personaRepo;
  @InjectMocks PersonaService personaService;

  PersonaInputDTO personaInputDTO = new PersonaInputDTO();

  Logger log = Logger.getLogger("MyLog");

  @BeforeAll
  public void iniciar() {
    crearPersonaInputDTO(personaInputDTO);
    log.info(personaInputDTO.toString());
  }

  @AfterAll
  public void finalizar() {
    personaRepo.deleteAll();
  }

  @Test
  public void bienCreado() {
    personaInputDTO.toString();
    String usuario = "usuario123";
    Assertions.assertEquals(personaInputDTO.getUsuario(), usuario);
  }

  @Test
  @DisplayName("Test Crear Persona")
  void crearPersonaTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(personaInputDTO);

    MvcResult resultado =
        this.mockMvc
            .perform(post("/persona").contentType(MediaType.APPLICATION_JSON).content(requestJson))
            .andExpect(status().isOk())
            .andReturn();

    String contenido = resultado.getResponse().getContentAsString();

    PersonaOutputDTO personaOutputDto =
        new ObjectMapper().readValue(contenido, new TypeReference<PersonaOutputDTO>() {});
    log.info(personaOutputDto.toString());

    Assertions.assertEquals(personaOutputDto.getUsuario(), personaInputDTO.getUsuario());
    Assertions.assertEquals(personaOutputDto.getPassword(), personaInputDTO.getPassword());
    Assertions.assertEquals(personaOutputDto.getName(), personaInputDTO.getName());
    Assertions.assertEquals(personaOutputDto.getCity(), personaInputDTO.getCity());
    Assertions.assertEquals(personaOutputDto.getActive(), personaInputDTO.getActive());
    Assertions.assertEquals(personaOutputDto.getUsuario(), personaInputDTO.getUsuario());
    Assertions.assertEquals(personaOutputDto.getUsuario(), personaInputDTO.getUsuario());
    Assertions.assertEquals(
        personaOutputDto.getCompany_email(), personaInputDTO.getCompany_email());
    Assertions.assertEquals(
        personaOutputDto.getPersonal_email(), personaInputDTO.getPersonal_email());
    Assertions.assertEquals(personaOutputDto.getCreated_date(), personaInputDTO.getCreated_date());
    Assertions.assertEquals(
        personaOutputDto.getTermination_date(), personaInputDTO.getTermination_date());
    Assertions.assertEquals(personaOutputDto.getImagen_url(), personaInputDTO.getImagen_url());
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
}
