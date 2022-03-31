package com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.EJ2CRUD_VALIDACION.Persona.application.IPersona;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.imput.PersonaImputDTO;
import com.example.EJ2CRUD_VALIDACION.Persona.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("persona")
public class ControladorPersona {

  @Autowired
  IPersona personaService;

  @PostMapping()
  public PersonaOutputDTO addPersona(@RequestBody PersonaImputDTO personaDTO) throws Exception {
    return personaService.añadirUsuario(personaDTO);
  }

  @GetMapping("{id}")
  public PersonaOutputDTO findById(@PathVariable Integer id) throws Exception {
    return personaService.findById(id);
  }

  @GetMapping()
  public List<PersonaOutputDTO> getAll() {
    return personaService.getAllPersonas();
  }

  @GetMapping("{usuario}/usuario")
  public List<PersonaOutputDTO> buscarPersonaUsuario(@PathVariable String usuario) {
    return personaService.findByUsuario(usuario);
  }

  @DeleteMapping("{id}")
  public void borrarPersona(@PathVariable Integer id) throws Exception {
    personaService.deletePersona(id);
  }

  @PutMapping("update/{id}")
  public PersonaOutputDTO actualizarUsuario(@PathVariable Integer id, @RequestBody PersonaImputDTO personaImputDTO) throws Exception {
    return personaService.updatePersona(id,personaImputDTO);
  }

  @GetMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);

    if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
      try{
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        PersonaOutputDTO user = personaService.findByUsuario(username).get(0);
        Map<String, String> role = new HashMap<>();
        if(user.getAdmin()){
          role.put("roles", "ADMIN");
        }else{
          role.put("roles", "USER");

        }

        String refresh_token = JWT.create()
                .withSubject(user.getUsuario())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 *60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", role)
                .sign(algorithm);


        Map<String, String> tokens = new HashMap<>();
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);


      }catch(Exception e){
        response.setHeader("error", e.getMessage());
        response.setStatus(FORBIDDEN.value());

        Map<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);

      }
    }else{
      throw new RuntimeException("Refresh token is missing");
    }

  }



}


