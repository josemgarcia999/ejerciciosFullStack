package com.example.BS3;

import org.springframework.boot.CommandLineRunner;

public class claseTercera implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
    System.out.println("Hola desde la tercera clase");
    System.out.println("Lista de argumentos (Num de argumentos = "+args.length+")");
    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
    }
    }
}
