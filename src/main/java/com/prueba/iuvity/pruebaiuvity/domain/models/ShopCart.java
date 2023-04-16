package com.prueba.iuvity.pruebaiuvity.domain.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopCart {

    private Long id;
    private UsuarioModel usuario;
    private Product product;
    private int cantidad;
    private double total;

}
