package com.example.EJ2CRUD_VALIDACION.Persona.application;

import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PersonaService implements IPersona {

  @Autowired JdbcOperations jdbc;

  @Override
  public PersonaOutputDTO añadirUsuario(PersonaImputDTO persona) throws Exception {
    PersonaEntity p = new PersonaEntity(persona);
    Date d = new Date();

    final String sql =
        "insert into persona(id,usuario,password,name,surname,company_email,personal_email,city,active,created_date,imagen_url,termination_Date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
    jdbc.update(
        con -> {
          PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
          ps.setInt(1, persona.getId());
          ps.setString(2, persona.getUsuario());
          ps.setString(3, persona.getPassword());
          ps.setString(4, persona.getName());
          ps.setString(5, persona.getSurname());
          ps.setString(6, persona.getCompany_email());
          ps.setString(7, persona.getPersonal_email());
          ps.setString(8, persona.getCity());
          ps.setBoolean(9, persona.getActive());
          ps.setDate(10, new java.sql.Date(d.getTime()));
          ps.setString(11, persona.getImagen_url());
          ps.setDate(12, new java.sql.Date(d.getTime()));
          return ps;
        });

    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);
    return personaOutputDTO;
  }

  @Override
  public PersonaOutputDTO findById(Integer id) throws Exception {
    PersonaEntity p =
        jdbc.queryForObject("select * from persona where id=?", new personaRowMapper(), id);
    PersonaOutputDTO persona = new PersonaOutputDTO(p);
    return persona;
  }

  @Override
  public List<PersonaOutputDTO> getAllPersonas() {
    try {
      List<PersonaEntity> personasEntities = jdbc.queryForObject("select * from persona", new personaListaRowMapper());
      List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
      for(PersonaEntity p:personasEntities){
          PersonaOutputDTO per = new PersonaOutputDTO(p);
          personaOutputDTOList.add(per);
      }
      return personaOutputDTOList;
    } catch (EmptyResultDataAccessException e) {
      return new ArrayList<>();
    }
  }

    @Override
    public List<PersonaOutputDTO> findByUser(String usuario) {
        try {
            List<PersonaEntity> personasEntities = jdbc.queryForObject("select * from persona where usuario=?", new personaListaRowMapper(),usuario);
            List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
            for(PersonaEntity p:personasEntities){
                PersonaOutputDTO per = new PersonaOutputDTO(p);
                personaOutputDTOList.add(per);
            }
            return personaOutputDTOList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
  public void deletePersona(Integer id) throws Exception {
    String consultaBorrar = "delete from persona where id=" + id;
    jdbc.execute(consultaBorrar);
  }

  @Override
  public PersonaOutputDTO updatePersona(Integer id, PersonaImputDTO persona) {
    String sql =
        "update persona set usuario=?,password=?,name=?,surname=?,company_email=?,personal_email=?,city=?,active=?,created_date=?,imagen_url=?,termination_Date=? where id=?";
    jdbc.update(
        con -> {
          PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
          ps.setInt(12, id);
          ps.setString(1, persona.getUsuario());
          ps.setString(2, persona.getPassword());
          ps.setString(3, persona.getName());
          ps.setString(4, persona.getSurname());
          ps.setString(5, persona.getCompany_email());
          ps.setString(6, persona.getPersonal_email());
          ps.setString(7, persona.getCity());
          ps.setBoolean(8, persona.getActive());
          ps.setDate(9,
              (persona.getCreated_date() == null)
                  ? null
                  : new java.sql.Date(persona.getCreated_date().getTime()));
          ps.setString(10, persona.getImagen_url());
          ps.setDate(
              11,
              (persona.getTermination_date() == null)
                  ? null
                  : new java.sql.Date(persona.getTermination_date().getTime()));
          return ps;
        });
    PersonaEntity p = jdbc.queryForObject("select * from persona where id=?", new personaRowMapper(), id);
    PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);

    return personaOutputDTO;
  }

  private class personaRowMapper implements RowMapper<PersonaEntity> {
    @Override
    public PersonaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new PersonaEntity(
          rs.getInt("id"),
          rs.getString("usuario"),
          rs.getString("password"),
          rs.getString("name"),
          rs.getString("surname"),
          rs.getString("company_email"),
          rs.getString("personal_email"),
          rs.getString("city"),
          rs.getBoolean("active"),
          rs.getDate("created_date"),
          rs.getString("imagen_url"),
          rs.getDate("termination_Date"));
    }
  }

  private class personaListaRowMapper implements RowMapper<List<PersonaEntity>> {

    @Override
    public List<PersonaEntity> mapRow(ResultSet rs, int rowNum) throws SQLException {
      ArrayList<PersonaEntity> listaPersonas = new ArrayList();
      do {
        listaPersonas.add(
            new PersonaEntity(
                rs.getInt("id"),
                rs.getString("usuario"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("company_email"),
                rs.getString("personal_email"),
                rs.getString("city"),
                rs.getBoolean("active"),
                rs.getDate("created_date"),
                rs.getString("imagen_url"),
                rs.getDate("termination_Date")));

      } while (rs.next());
      return listaPersonas;
    }
  }
}
