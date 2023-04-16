package com.prueba.iuvity.pruebaiuvity.infrastructure.repositories;

import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ShopCartEntity;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaShopCartRepository extends JpaRepository<ShopCartEntity,Long> {

    List<ShopCartEntity> findByUsuarioEntity(Usuario usuario);
}
