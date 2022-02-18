package com.example.EJ2CRUD_VALIDACION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class PersonaService implements IPersona{

    @Autowired
    PersonaRepo personaRepo;

    @Override
    public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception {
        persona.comprobarDTO();
        PersonaEntity p = new PersonaEntity(persona);
        personaRepo.save(p);
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
        return personaOutputDTO;
    }

    @Override
    public PersonaOutputDTO buscarPersonaID(Integer id) {
        PersonaEntity p = personaRepo.findById(id).orElseThrow(null);
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
    @Override
    public void borrarUsuario(Integer id) throws Exception {
        personaRepo.delete(personaRepo.findById(id).orElseThrow(() ->new Exception("Id no encontrado")));
    }

    @Override
    public PersonaOutputDTO modificarPersona(Integer id, PersonaImputDTO personaImputDTO) throws Exception {

        PersonaEntity p = personaRepo.findById(id).orElseThrow(() ->new Exception("Id no encontrado"));
        if (personaImputDTO.getUsuario() !=null){
            p.setUsuario(personaImputDTO.getUsuario());
        }
        if (personaImputDTO.getPassword() !=null){
            p.setPassword(personaImputDTO.getPassword());
        }
        if (personaImputDTO.getName() !=null){
            p.setName(personaImputDTO.getName());
        }
        if (personaImputDTO.getSurname() !=null){
            p.setSurname(personaImputDTO.getSurname());
        }
        if (personaImputDTO.getCompany_email() !=null){
            p.setCompany_email(personaImputDTO.getCompany_email());
        }
        if (personaImputDTO.getPersonal_email() !=null){
            p.setPersonal_email(personaImputDTO.getPersonal_email());
        }
        if (personaImputDTO.getCity() !=null){
            p.setCity(personaImputDTO.getCity());
        }
        if (personaImputDTO.getActive() !=null){
            p.setActive(personaImputDTO.getActive());
        }
        if (personaImputDTO.getImagen_url() != null){
            p.setImagen_url(personaImputDTO.getImagen_url());
        }

        if (personaImputDTO.getTermination_date() != null){
            p.setTermination_date(personaImputDTO.getTermination_date());
        }

        personaRepo.saveAndFlush(p);
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
        return personaOutputDTO;


    }


}
