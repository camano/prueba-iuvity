package com.prueba.iuvity.pruebaiuvity.infrastructure.repositories;

import com.prueba.iuvity.pruebaiuvity.domain.dto.CartUsuario;
import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.domain.ports.out.ShopCartRepositoryPort;
import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ProductEntity;
import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ShopCartEntity;
import com.prueba.iuvity.pruebaiuvity.infrastructure.exception.ShopCardException;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.ShopCartMapper;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.UsuarioMapper;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaShopCartRepositoryImpl implements ShopCartRepositoryPort {
    private final static Logger logger = LoggerFactory.getLogger(JpaShopCartRepositoryImpl.class);
    private final JpaShopCartRepository jpaShopCartRepository;


    private final JpaProductRepository jpaProductRepository;


    public JpaShopCartRepositoryImpl(@Lazy JpaShopCartRepository jpaShopCartRepository, JpaProductRepository jpaProductRepository) {
        this.jpaShopCartRepository = jpaShopCartRepository;

        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public ShopCart addCart(ShopCart shopCart) {
        try {
            Usuario usuarioEntity = UsuarioMapper.fromDomainModelUsuario(shopCart.getUsuario());
            ProductEntity productEntity = jpaProductRepository.findById(shopCart.getProduct().getIdProduct()).orElseThrow(() -> new ShopCardException(HttpStatus.BAD_REQUEST, "No se encontro el producto"));
            ShopCartEntity shopCartEntity = ShopCartMapper.fromDomainModelShopCart(shopCart);
            shopCartEntity.setUsuarioEntity(usuarioEntity);
            shopCartEntity.setProductEntity(productEntity);

            ShopCartEntity addCartShop = jpaShopCartRepository.save(shopCartEntity);
            logger.info("Se añadio al carrito :: " + addCartShop);
            return ShopCartMapper.toDomainModelShopCart(addCartShop);
        } catch (DataAccessException dataAccessException) {
            logger.error("Error en Añadir al carrito de compra :: " + dataAccessException.getMessage());
            throw new ShopCardException(HttpStatus.BAD_GATEWAY, "Hubo un error :: " + dataAccessException.getMessage());
        }

    }

    @Override
    public List<ShopCart> getCartUsuario(Usuario usuario) {
        try {
            return jpaShopCartRepository.findByUsuarioEntity(usuario).stream().map(ShopCartMapper::toDomainModelShopCart).collect(Collectors.toList());

        } catch (DataAccessException dataAccessException) {
            logger.error("Error a consultar el carrito de compra :: " + dataAccessException.getMessage());
            throw new ShopCardException(HttpStatus.BAD_GATEWAY, "Error a consultar el carrito de compra ::" + dataAccessException.getMessage());
        }
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
