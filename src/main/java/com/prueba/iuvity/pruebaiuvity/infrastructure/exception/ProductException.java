package com.prueba.iuvity.pruebaiuvity.infrastructure.exception;


import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductException extends RuntimeException{
    private HttpStatus estado;
    private String message;


}
