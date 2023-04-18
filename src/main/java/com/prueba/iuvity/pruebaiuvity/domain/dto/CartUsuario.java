package com.prueba.iuvity.pruebaiuvity.domain.dto;

import com.prueba.iuvity.pruebaiuvity.domain.models.Product;
import com.prueba.iuvity.pruebaiuvity.domain.models.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartUsuario {

    public UsuarioModel usuarioModel;

    public Product products;

    public double total;

}
