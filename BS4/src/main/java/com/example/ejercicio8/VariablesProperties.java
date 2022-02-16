package com.example.ejercicio8;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class VariablesProperties {

    @Value("${VAR1}")
    private String var1;

    @Value("${my.var2}")
    private String var2;

    @Value("${VAR_SISTEMA:no tiene valor}")
    private String var3;

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }
}
