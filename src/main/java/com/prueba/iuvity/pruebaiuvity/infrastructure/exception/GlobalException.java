package com.prueba.iuvity.pruebaiuvity.infrastructure.exception;


import com.prueba.iuvity.pruebaiuvity.domain.dto.Mensajes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<?> manejarProductException(ProductException productException) {
        Mensajes mensajes = new Mensajes(productException.getMessage());
        return new ResponseEntity<>(mensajes, productException.getEstado());
    }
    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<Mensajes> manejarUsuarioException(UsuarioException usuarioException) {
        Mensajes mensajes = new Mensajes(usuarioException.getMensaje());
        return new ResponseEntity<>(mensajes, usuarioException.getEstado());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Mensajes> manejarGlobalException(Exception exception, WebRequest webRequest) {

        Mensajes mensajeDto = new Mensajes(exception.getMessage());
        return new ResponseEntity<>(mensajeDto, HttpStatus.BAD_REQUEST);
    }
}
