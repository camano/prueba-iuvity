package com.prueba.iuvity.pruebaiuvity.aplication.services;

import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.domain.ports.in.ShopCartUseCase;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class ShopCartService implements ShopCartUseCase {


    private final ShopCartUseCase shopCartUseCase;

    public ShopCartService(@Lazy ShopCartUseCase shopCartUseCase) {
        this.shopCartUseCase = shopCartUseCase;
    }

    @Override
    public ShopCart addCart(ShopCart shopCart) {
        return shopCartUseCase.addCart(shopCart);
    }

    @Override
    public List<ShopCart> getCartUsuario(Usuario usuario) {
        return shopCartUseCase.getCartUsuario(usuario);
    }

    @Override
    public boolean deleteCart(Long id) {
        return shopCartUseCase.deleteCart(id);
    }
}
