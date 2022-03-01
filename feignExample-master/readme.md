## Pruebas con Feign y RESTMPLATE

Este programa al llamar al endpoint client llamara al endpoint server.



![Dependencias](.\dependencias.png)

#### Endpoints Feign

##### LLamara del client al server y no usara fallback.

- curl -o -  -w "\nStatus code:%{http_code}\n" http://localhost:8080/client/200

#####  Llamada de cliente al server y usara fallback ya que no devolera un 2xx
- curl -o -  -w "\nStatus code:%{http_code}\n" http://localhost:8080/client/401

##### Llamada de cliente al server y devolvera el httpCode mandado más 1. No usa fallback
- curl -o -  -w "\nStatus code:%{http_code}\n" http://localhost:8080/client/1/200

##### Llamada  normal. Llamada de cliente al server y devolvera fallback  con el httpCode mandado mas 1, ya que no devolvera un 2xx

- curl -o -  -w "\nStatus code:%{http_code}\n" http://localhost:8080/client/1/401

#### Endpoints Restemplate

##### Llamada usando RestTemplate sin dar error.

- curl -o -  -w "\nStatus code:%{http_code}\n" http://localhost:8080/client/template/200





