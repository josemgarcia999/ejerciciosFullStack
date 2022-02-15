package com.example.ejercicio9;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:miconfiguracion.properties")
public class MiConfiguracion {

    @Value("${valor1}")
    String valor1;
    @Value("${valor2}")
    String valor2;

}
