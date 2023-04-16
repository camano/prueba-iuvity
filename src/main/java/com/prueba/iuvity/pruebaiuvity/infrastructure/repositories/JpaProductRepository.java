package com.prueba.iuvity.pruebaiuvity.infrastructure.repositories;


import com.prueba.iuvity.pruebaiuvity.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity,Long> {
}
