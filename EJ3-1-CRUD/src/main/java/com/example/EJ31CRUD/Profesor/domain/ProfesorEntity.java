package com.example.EJ31CRUD.Profesor.domain;


import com.example.EJ31CRUD.Persona.domain.PersonaEntity;
import com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.imput.ProfesorImputDTO;
import com.example.EJ31CRUD.Student.domain.StudentEntity;
import com.example.EJ31CRUD.configuration.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Profesores")
public class ProfesorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profesor_seq")
    @GenericGenerator(
            name = "profesor_seq",
            strategy = "com.example.EJ31CRUD.configuration.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PROF_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    @Column(name = "id")
    String idProfesor;

    @OneToOne(fetch = FetchType.LAZY)
    PersonaEntity persona;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    List<StudentEntity> estudiantes;

    String comments;
    String branch;


    public ProfesorEntity(ProfesorImputDTO profesorImputDTO) {
        if (profesorImputDTO == null) return;
        //setPersona(persona);
        setComments(profesorImputDTO.getComments());
        setBranch(profesorImputDTO.getBranch());
    }




}
