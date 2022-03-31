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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeletePersonaControllerTest {
    private PersonaInputDTO personaInputDTO;
    private PersonaEntity persona;
    private PersonaOutputDTO personaOutputDTO;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService personaService;


    @BeforeAll
    public void init() throws Exception {
        personaInputDTO = new PersonaInputDTO();
        personaInputDTO.setName("Testeando");
        personaInputDTO.setUsuario("Usuario");
        personaInputDTO.setCity("Ciudad");
        personaInputDTO.setPassword("Password");
        personaInputDTO.setPersonal_email("abg00044@ujaen.es");
        personaInputDTO.setCompany_email("abg00044@ujaen.es");
        personaInputDTO.setActive(true);
        personaInputDTO.setCreated_date(new Date());
        persona = new PersonaEntity(personaInputDTO);
        persona.setId(1);
        personaOutputDTO = new PersonaOutputDTO(persona);
    }



    @Test
    public void deleteByIdTest() throws Exception {
        doNothing().when(personaService).deletePersona(persona.getId());
        mockMvc.perform(delete("/persona/" + persona.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
