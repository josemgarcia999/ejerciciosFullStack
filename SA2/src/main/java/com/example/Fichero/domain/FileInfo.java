package com.example.Fichero.domain;


import com.example.Fichero.infraestructure.dto.input.FicheroInputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    private String name;
    private String url;
}