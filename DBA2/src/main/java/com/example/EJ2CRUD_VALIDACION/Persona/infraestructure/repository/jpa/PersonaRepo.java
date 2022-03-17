package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa;

import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Repository
public class PersonaRepo{
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
