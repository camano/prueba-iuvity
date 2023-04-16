package com.prueba.iuvity.pruebaiuvity.infrastructure.mapper;

import com.prueba.iuvity.pruebaiuvity.domain.models.Product;
import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.domain.models.UsuarioModel;
import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ShopCartEntity;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;

public class ShopCartMapper {

    public static ShopCartEntity fromDomainModelShopCart(ShopCart shopCart){
        ShopCartEntity shopCartEntity=new ShopCartEntity();
        shopCartEntity.setId(shopCart.getId());
        shopCartEntity.setCantidad(shopCart.getCantidad());
        shopCartEntity.setTotal(shopCart.getTotal());
        return shopCartEntity;
    }

    public static ShopCart toDomainModelShopCart(ShopCartEntity shopCartEntity){
        ShopCart shopCart=new ShopCart();
        UsuarioModel usuario=UsuarioMapper.toDomainModelUsuario(shopCartEntity.getUsuarioEntity());
        Product product=UsuarioMapper.toDomainModelProduct(shopCartEntity.getProductEntity());
        shopCart.setId(shopCartEntity.getId());
        shopCart.setUsuario(usuario);
        shopCart.setProduct(product);
        shopCart.setTotal(shopCartEntity.getTotal());
        shopCart.setCantidad(shopCartEntity.getCantidad());
        return shopCart;

    }
}
