package com.prueba.iuvity.pruebaiuvity.infrastructure.mapper;

import com.prueba.iuvity.pruebaiuvity.domain.models.Product;
import com.prueba.iuvity.pruebaiuvity.domain.models.UsuarioModel;
import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ProductEntity;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public static Usuario fromDomainModelUsuario(UsuarioModel usuarioModel) {
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setId(usuarioModel.getId());
        usuarioEntity.setNombre(usuarioModel.getNombre());
        usuarioEntity.setUsername(usuarioModel.getUsername());
        usuarioEntity.setPassword(usuarioModel.getPassword());
        return usuarioEntity;
    }

    public static UsuarioModel toDomainModelUsuario(Usuario usuarioEntity){
        UsuarioModel usuario =new UsuarioModel();
        usuario.setId(usuarioEntity.getId());
        usuario.setUsername(usuarioEntity.getUsername());
        usuario.setNombre(usuarioEntity.getNombre());
        usuario.setPassword(usuarioEntity.getPassword());
        return usuario;
    }

    public static ProductEntity fromDomainModelProduct(Product product){
        ProductEntity productEntity =new ProductEntity();
        productEntity.setIdProduct(product.getIdProduct());
        productEntity.setNombreProduct(product.getNombreProduct());
        productEntity.setDescripcionProduct(product.getDescripcionProduct());
        productEntity.setStock(product.getStock());
        productEntity.setPrecio(product.getPrecio());
        return productEntity;
    }

    public static Product toDomainModelProduct(ProductEntity productEntity){
        Product product=new Product();
        UsuarioModel usuario=UsuarioMapper.toDomainModelUsuario(productEntity.getUsuarioEntity());
        product.setIdProduct(productEntity.getIdProduct());
        product.setNombreProduct(productEntity.getNombreProduct());
        product.setDescripcionProduct(productEntity.getDescripcionProduct());
        product.setStock(productEntity.getStock());
        product.setPrecio(productEntity.getPrecio());
        product.setUsuario(usuario);
        return product;
    }

}
