package com.example.BS3;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MiComponente {

    @PostConstruct
    void inicio(){
    System.out.println("Hola desde la clase principal");
    }



}
