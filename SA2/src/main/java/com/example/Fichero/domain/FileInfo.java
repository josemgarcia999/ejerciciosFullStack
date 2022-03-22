package com.example.Fichero.domain;


import com.example.Fichero.infraestructure.dto.input.FicheroInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    private String nombre;
    private String url;
    private String extension;
    Date fecha;

    public FileInfo(String nombre, String url, String extension){
        setId(id);
        setNombre(nombre);
        setExtension(extension);
        setUrl(url);
        fecha = new Date();

    }

}