package com.example.jpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class Controlador {
    @Autowired
    UsuarioRepo usuarioRepo;

    @GetMapping
    public List<Usuario> getAll(){
        return usuarioRepo.findAll();

    }

    @PostMapping
    public Usuario añadirUsuario(@RequestBody Usuario usu){
    System.out.println("En el controlador de post");
        usuarioRepo.save(usu);
    return usu;
    }

    @GetMapping("{id}")
    public Usuario getByID(@PathVariable String id) throws Exception {
        return usuarioRepo.findById(id).orElseThrow(()-> new Exception("No encontrado"));

    }


    //AÑADIR DELETE Y UPDATE

}
