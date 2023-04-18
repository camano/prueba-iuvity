package com.prueba.iuvity.pruebaiuvity.infrastructure.exception;


import com.prueba.iuvity.pruebaiuvity.domain.dto.Mensajes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException  extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<?> manejarProductException(ProductException productException) {
        Mensajes mensajes = new Mensajes(productException.getMessage());
        return new ResponseEntity<>(mensajes, productException.getEstado());
    }
    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<?> manejarUsuarioException(UsuarioException usuarioException) {
        Mensajes mensajes = new Mensajes(usuarioException.getMessage());
        return new ResponseEntity<>(mensajes, usuarioException.getEstado());
    }
    @ExceptionHandler(ShopCardException.class)
    public ResponseEntity<?> manejarShopCardException(ShopCardException shopCardException) {
        Mensajes mensajes = new Mensajes(shopCardException.getMessage());
        return new ResponseEntity<>(mensajes, shopCardException.getEstado());
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();

            errores.put(nombreCampo, mensaje);
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
