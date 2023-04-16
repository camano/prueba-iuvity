package com.prueba.iuvity.pruebaiuvity.domain.ports.out;

import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;

import java.util.List;

public interface ShopCartRepositoryPort {

    ShopCart addCart(ShopCart shopCart);

    List<ShopCart> getCartUsuario(Usuario usuario);

    boolean deleteCart(Long id);
}
