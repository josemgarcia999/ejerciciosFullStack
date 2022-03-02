package com.example.EJ31CRUD.Profesor.infraestructure.controller.dto.output;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class ProfesorOutputDTOList {
    private List<ProfesorOutputDTO> profesorOutputDTOList;
}
