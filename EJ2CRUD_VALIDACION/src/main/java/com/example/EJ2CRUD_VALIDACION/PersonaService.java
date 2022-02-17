package com.example.EJ2CRUD_VALIDACION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonaService implements IPersona{

    @Autowired
    PersonaRepo personaRepo;

    @Override
    public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception {
        PersonaEntity p = new PersonaEntity(persona);
        personaRepo.save(p);
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
        return personaOutputDTO;
    }

    @Override
    public PersonaOutputDTO buscarPersonaID(Integer id) {
        PersonaEntity p = personaRepo.findById(id).orElseThrow(null);
    System.out.println("DENTRO DE BUSCARPERSONAID PERSONASERVICE");
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
        return personaOutputDTO;
    }

    @Override
    public List<PersonaOutputDTO> mostrarTodasPersonas() {
        List<PersonaEntity> personasEntities = personaRepo.findAll();
        List<PersonaOutputDTO> personas = new ArrayList<>();
        for (PersonaEntity p : personasEntities) {
            PersonaOutputDTO per = new PersonaOutputDTO(p);
            personas.add(per);
        }
        return personas;
    }

    @Override
    public List<PersonaOutputDTO> buscarPersonaUsuario(String usuario) {
        List<PersonaEntity> personas = personaRepo.findAll();
        List<PersonaOutputDTO> personaOutputDTOS = new ArrayList<>();
        for(PersonaEntity p: personas){
            if(p.getUsuario().equalsIgnoreCase(usuario)){
                PersonaOutputDTO persona = new PersonaOutputDTO(p);
                personaOutputDTOS.add(persona);
            }

        }
        return personaOutputDTOS;
    }


}
