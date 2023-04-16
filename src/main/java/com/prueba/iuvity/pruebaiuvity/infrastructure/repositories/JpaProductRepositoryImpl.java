package com.prueba.iuvity.pruebaiuvity.infrastructure.repositories;

import com.prueba.iuvity.pruebaiuvity.domain.models.Product;
import com.prueba.iuvity.pruebaiuvity.domain.models.UsuarioModel;
import com.prueba.iuvity.pruebaiuvity.domain.ports.out.ProductRepositoryPort;
import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ProductEntity;
import com.prueba.iuvity.pruebaiuvity.infrastructure.exception.GlobalException;
import com.prueba.iuvity.pruebaiuvity.infrastructure.exception.ProductException;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.UsuarioMapper;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import com.prueba.iuvity.pruebaiuvity.security.jwt.JwtTokenProvider;
import com.prueba.iuvity.pruebaiuvity.security.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaProductRepositoryImpl implements ProductRepositoryPort {


    private final JpaProductRepository jpaProductRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JpaProductRepositoryImpl(@Lazy JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;

    }


    @Override
    public Product save(Product product) {

        Usuario usuario =usuarioRepositorio.findById(product.getUsuario().getId()).orElse(null);
        ProductEntity productEntity = UsuarioMapper.fromDomainModelProduct(product);
        productEntity.setUsuarioEntity(usuario);
        ProductEntity createProduct = jpaProductRepository.save(productEntity);
        return UsuarioMapper.toDomainModelProduct(createProduct);

    }

    @Override
    public List<Product> getAllProducts() {
        return jpaProductRepository.findAll().stream().map(UsuarioMapper::toDomainModelProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductsById(Long id) {
        return jpaProductRepository.findById(id).map(UsuarioMapper::toDomainModelProduct);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        try {
            if (jpaProductRepository.existsById(id)) {
                Usuario usuario =usuarioRepositorio.findById(product.getUsuario().getId()).orElse(null);
                ProductEntity productEntity = UsuarioMapper.fromDomainModelProduct(product);
                productEntity.setIdProduct(id);
                productEntity.setUsuarioEntity(usuario);
                ProductEntity updateProduct = jpaProductRepository.save(productEntity);
                return Optional.of(UsuarioMapper.toDomainModelProduct(updateProduct));
            }
        }catch (DataIntegrityViolationException sqlException){
            throw new ProductException(HttpStatus.OK,"Errrpr");
        }

        return Optional.empty();
    }
}
