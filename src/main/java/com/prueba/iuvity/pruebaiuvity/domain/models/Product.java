package com.prueba.iuvity.pruebaiuvity.domain.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    private Long idProduct;
    private String nombreProduct;
    private String descripcionProduct;
    private int stock;
    private double precio;

    private UsuarioModel usuario;


}
