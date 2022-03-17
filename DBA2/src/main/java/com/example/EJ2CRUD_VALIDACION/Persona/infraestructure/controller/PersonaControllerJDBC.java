//package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller;
//
//import com.example.EJ2CRUD_VALIDACION.Persona.domain.PersonaEntity;
//import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.JdbcOperations;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Repository
//public class PersonaControllerJDBC {
//  private JdbcOperations jdbc;
//
//  @Bean
//  JdbcTemplate jdbcTemplate(DataSource dataSource) {
//    return new JdbcTemplate(dataSource);
//  }
//
//  // GET
//  public PersonaEntity findByPersonaId(Integer id) {
//    return jdbc.queryForObject(
//        "select * from PersonaEntity where id=?", new personaRowMapper(), id);
//  }
//
//  public List<PersonaEntity> findAllPersonas() {
//    return jdbc.queryForObject("select * from PersonaEntity ", new personaListaRowMapper());
//  }
//
//  public List<PersonaEntity> findPersonasByUsuario(String user) {
//    return jdbc.queryForObject(
//        "select * from PersonaEntity where usuario=?", new personaListaRowMapper(), user);
//  }
//  // DELETE
//  public void deletePersonaById(Integer id) {
//    String consultaBorrar = "delete from PersonaEntity where id=" + id;
//    jdbc.execute(consultaBorrar);
//  }
//
//  public void deleteAll() {
//    String consultaBorrarTodo = "delete from PersonaEntity";
//    jdbc.execute(consultaBorrarTodo);
//  }
//
//  public PersonaEntity actualizarPersona(Integer id, PersonaEntity persona) {
//    String sql =
//        "update persona set user=?,password=?,name=?,surname=?,company_email=?,personal_email=?,city=?,active=?,created_date=?,image_url=?,termination_date=? where id=?";
//    jdbc.update(
//        con -> {
//          PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
//          ps.setInt(12, id);
//          ps.setString(1, persona.getUsuario());
//          ps.setString(2, persona.getPassword());
//          ps.setString(3, persona.getName());
//          ps.setString(4, persona.getSurname());
//          ps.setString(5, persona.getCompany_email());
//          ps.setString(6, persona.getPersonal_email());
//          ps.setString(7, persona.getCity());
//          ps.setBoolean(8, persona.getActive());
//          ps.setDate(
//              9,
//              (persona.getCreated_date() == null)
//                  ? null
//                  : new java.sql.Date(persona.getCreated_date().getTime()));
//          ps.setString(10, persona.getImagen_url());
//          ps.setDate(
//              11,
//              (persona.getTermination_date() == null)
//                  ? null
//                  : new java.sql.Date(persona.getTermination_date().getTime()));
//          return ps;
//        });
//    return jdbc.queryForObject(
//        "select * from persona where id_persona=?", new personaRowMapper(), id);
//  }
//  // POST
//  public void insertarPersona(PersonaEntity persona) {
//    jdbc.update(
//        "INSERT INTO PersonaEntity (id,usuario, password,name,surname,company_email,personal_email,city,active,created_date,imagen_url,termination_Date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
//        persona.getId(),
//        persona.getUsuario(),
//        persona.getPassword(),
//        persona.getName(),
//        persona.getSurname(),
//        persona.getCompany_email(),
//        persona.getPersonal_email(),
//        persona.getCity(),
//        persona.getActive(),
//        persona.getCreated_date(),
//        persona.getImagen_url(),
//        persona.getTermination_date());
//  }
//
//  private class personaRowMapper implements RowMapper<PersonaEntity> {
//
//    @Override
//    public PersonaEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
//      return new PersonaEntity(
//          rs.getInt("id"),
//          rs.getString("usuario"),
//          rs.getString("password"),
//          rs.getString("name"),
//          rs.getString("surname"),
//          rs.getString("company_email"),
//          rs.getString("personal_email"),
//          rs.getString("city"),
//          rs.getBoolean("active"),
//          rs.getDate("created_date"),
//          rs.getString("imagen_url"),
//          rs.getDate("termination_Date"));
//    }
//  }
//
//  private class personaListaRowMapper implements RowMapper<List<PersonaEntity>> {
//
//    @Override
//    public List<PersonaEntity> mapRow(ResultSet rs, int rowNum) throws SQLException {
//      ArrayList<PersonaEntity> listaPersonas = new ArrayList();
//      do {
//        listaPersonas.add(
//            new PersonaEntity(
//                rs.getInt("id"),
//                rs.getString("usuario"),
//                rs.getString("password"),
//                rs.getString("name"),
//                rs.getString("surname"),
//                rs.getString("company_email"),
//                rs.getString("personal_email"),
//                rs.getString("city"),
//                rs.getBoolean("active"),
//                rs.getDate("created_date"),
//                rs.getString("imagen_url"),
//                rs.getDate("termination_Date")));
//
//      } while (rs.next());
//      return listaPersonas;
//    }
//  }
//}
