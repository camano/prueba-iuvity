package com.prueba.iuvity.pruebaiuvity.domain.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioModel {

    private long id;

    private String nombre;
    private String username;
    private String email;
    private String password;
}
