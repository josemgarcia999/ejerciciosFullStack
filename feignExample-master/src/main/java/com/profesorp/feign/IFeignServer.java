package com.profesorp.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="simpleFeign",url="http://localhost:8080/",fallbackFactory = FeignFallbackFactory.class)
public interface IFeignServer {

    @GetMapping("server/{httpCode}")
    ResponseEntity<OutputDto> callServer(@PathVariable("httpCode") int httpCode);

    @GetMapping("server/1/{httpCode}")
    ResponseEntity<OutputDto> callServerMas1(@PathVariable("httpCode") int httpCode);
}
