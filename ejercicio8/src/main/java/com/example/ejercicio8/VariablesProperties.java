package com.example.ejercicio8;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class VariablesProperties {

    @Value("${VAR1}")
    private String var1;

    @Value("${my.var2}")
    private String var2;

    @Value("${var3:var3 no tiene valor}")
    private String var3;






}
