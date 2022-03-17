package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.repository.jpa;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;


@Repository
public class PersonaRepositoryImpl {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }


}

