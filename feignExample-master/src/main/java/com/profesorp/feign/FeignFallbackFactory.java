package com.profesorp.feign;

import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component

public class FeignFallbackFactory implements FallbackFactory<IFeignServer> {
    @Override
    public IFeignServer create(Throwable cause) {
        return new FeingFallback(cause);
    }

}

class FeingFallback implements IFeignServer {
    int code;
    String msg;

    FeingFallback(Throwable cause)
    {
        if (cause instanceof FeignException)
        {
            FeignException feignException = (FeignException) cause;
            code=feignException.status();

            //System.out.println("Devolvio status: "+ feignException.status()+ " Mensaje: "+feignException.getLocalizedMessage());
        }
        msg=cause.getLocalizedMessage();
    }
    @Override
    public ResponseEntity<OutputDto> callServer(int httpCode) {
       return ResponseEntity.status(code).body(new OutputDto(httpCode, "Devuelto por fallback. Code: "+code+ " -> "+msg));
    }
    @Override
    public ResponseEntity<OutputDto> callServerMas1(@PathVariable("httpCode") int httpCode)
    {
        return ResponseEntity.status(code).body(new OutputDto(httpCode, "Devuelto por fallbackPlusOne  Code: "+code+ " -> "+msg));
    }
};
