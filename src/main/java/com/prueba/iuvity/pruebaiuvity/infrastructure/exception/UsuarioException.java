package com.prueba.iuvity.pruebaiuvity.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioException extends RuntimeException{
    private HttpStatus estado;
    private String mensaje;
}
