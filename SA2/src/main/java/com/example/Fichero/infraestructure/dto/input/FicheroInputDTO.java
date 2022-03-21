package com.example.Fichero.infraestructure.dto.input;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheroInputDTO {

    String nombre;
    String rutaFichero;
    Date fechaSubida;
    String categoria;//metadato


}
