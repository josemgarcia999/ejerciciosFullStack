package com.example.ejercicio8;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;


@Data
@Component
@ConfigurationProperties(prefix = "prueba")
public class VariablesYML {

    private String var1;
    private String my_var2;
    private String var3;


}
