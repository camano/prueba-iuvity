package com.prueba.iuvity.pruebaiuvity.aplication.usecases;

import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.domain.ports.in.ShopCartUseCase;
import com.prueba.iuvity.pruebaiuvity.domain.ports.out.ShopCartRepositoryPort;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class ShopUseCaseImpl implements ShopCartUseCase {

    private final ShopCartRepositoryPort shopCartRepositoryPort;

    public ShopUseCaseImpl(@Lazy ShopCartRepositoryPort shopCartRepositoryPort) {
        this.shopCartRepositoryPort = shopCartRepositoryPort;
    }


    @Override
    public ShopCart addCart(ShopCart shopCart) {
        return shopCartRepositoryPort.addCart(shopCart);
    }

    @Override
    public List<ShopCart> getCartUsuario(Usuario usuario) {
        return shopCartRepositoryPort.getCartUsuario(usuario);
    }

    @Override
    public boolean deleteCart(Long id) {
        return shopCartRepositoryPort.deleteCart(id);
    }
}
