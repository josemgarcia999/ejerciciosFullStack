package com.example.jpademo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    String id;


    String nombreUsuario;
    int edad;
    Date fecha; //año-mes-dia-> yyyy-mm-dd
    String ciudad;
    String pais;


}
