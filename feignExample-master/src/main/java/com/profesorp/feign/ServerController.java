package com.profesorp.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("server")
public class ServerController {

    @GetMapping("{httpCode}")
    ResponseEntity<OutputDto> getHttpCode(@PathVariable int httpCode)
    {
        System.out.println("En server. Devolvere: "+httpCode);
        return ResponseEntity.status(httpCode).body(new OutputDto(httpCode,"return by server"));
    }

    /**
     *
     * @param httpCode
     * @return Return code received plus one.
     */
    @GetMapping("1/{httpCode}")
    ResponseEntity<OutputDto> getHttpCodePlusOne(@PathVariable int httpCode)
    {
        System.out.println("En server. Devolvere: "+httpCode);
        return ResponseEntity.status(httpCode+1).body(new OutputDto(httpCode+1,"return by server"));
    }
}
