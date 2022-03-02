package com.example.EJ31CRUD.Student.infraestructure.controller.dto.output;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class StudentOutputDTOList {
    private List<StudentOutputDTO> studentOutputDTOList;

}
