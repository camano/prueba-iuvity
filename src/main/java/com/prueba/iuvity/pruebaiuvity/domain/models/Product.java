package com.prueba.iuvity.pruebaiuvity.domain.models;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    private Long idProduct;
    private String nombreProduct;
    private String descripcionProduct;
    @NotNull
    @Min(value = 1)
    private int stock;
    private double precio;

    private UsuarioModel usuario;


}
