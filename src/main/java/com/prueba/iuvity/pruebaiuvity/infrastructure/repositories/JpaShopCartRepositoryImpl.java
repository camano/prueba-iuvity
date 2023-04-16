package com.prueba.iuvity.pruebaiuvity.infrastructure.repositories;

import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.domain.ports.out.ShopCartRepositoryPort;
import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ProductEntity;
import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ShopCartEntity;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.ShopCartMapper;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.UsuarioMapper;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaShopCartRepositoryImpl implements ShopCartRepositoryPort {

    private final JpaShopCartRepository jpaShopCartRepository;


    private final JpaProductRepository jpaProductRepository;


    public JpaShopCartRepositoryImpl(@Lazy JpaShopCartRepository jpaShopCartRepository, JpaProductRepository jpaProductRepository) {
        this.jpaShopCartRepository = jpaShopCartRepository;

        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public ShopCart addCart(ShopCart shopCart) {
        Usuario usuarioEntity = UsuarioMapper.fromDomainModelUsuario(shopCart.getUsuario());
        ProductEntity productEntity = jpaProductRepository.findById(shopCart.getProduct().getIdProduct()).orElse(null);
        ShopCartEntity shopCartEntity = ShopCartMapper.fromDomainModelShopCart(shopCart);
        shopCartEntity.setUsuarioEntity(usuarioEntity);
        shopCartEntity.setProductEntity(productEntity);

        ShopCartEntity addCartShop = jpaShopCartRepository.save(shopCartEntity);
        return ShopCartMapper.toDomainModelShopCart(addCartShop);
    }

    @Override
    public List<ShopCart> getCartUsuario(Usuario usuario) {

        return jpaShopCartRepository.findByUsuarioEntity(usuario).stream().map(ShopCartMapper::toDomainModelShopCart).collect(Collectors.toList());

    }

    @Override
    public boolean deleteCart(Long id) {
        if (jpaShopCartRepository.existsById(id)) {
            jpaShopCartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
