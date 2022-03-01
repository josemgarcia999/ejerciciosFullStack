package com.profesorp.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    IFeignServer iFeignServer;

    @GetMapping("{code}")
    ResponseEntity<OutputDto> callUsingFeign(@PathVariable int code)
    {
        System.out.println("En client. Antes de llamada a server Devolvere: "+code);
        ResponseEntity<OutputDto> respuesta=iFeignServer.callServer(code);
        System.out.println("En client. Despues de llamada a server");
        return respuesta;
    }
    @GetMapping("1/{code}")
    ResponseEntity<OutputDto> callUsingFeignPlusOne(@PathVariable int code)
    {
        System.out.println("En client. Antes de llamada a server Devolvere: "+code);
        ResponseEntity<OutputDto> respuesta=iFeignServer.callServerMas1(code);
        System.out.println("En client. Despues de llamada a server");
        return respuesta;
    }

    @GetMapping("/template/{code}")
    ResponseEntity<OutputDto> callUsingRestTemplate(@PathVariable int code)
    {
        System.out.println("En client Resttemplate. Antes de llamada a server Devolvere: "+code);
        ResponseEntity<OutputDto> rs = new RestTemplate().getForEntity("http://localhost:8080/server/"+code,OutputDto.class);
        System.out.println("En client Resttemplate. Despues de llamada a server");
        return ResponseEntity.ok(rs.getBody());
    }
}
