package com.prueba.iuvity.pruebaiuvity.domain.ports.in;

import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;

import java.util.List;

public interface ShopCartUseCase {

    ShopCart addCart(ShopCart shopCart);

    List<ShopCart> getCartUsuario(Usuario usuario);

    boolean deleteCart(Long id);
}
