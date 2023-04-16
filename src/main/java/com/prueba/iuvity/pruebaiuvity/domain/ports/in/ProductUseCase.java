package com.prueba.iuvity.pruebaiuvity.domain.ports.in;

import com.prueba.iuvity.pruebaiuvity.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductUseCase {

    Product createProduct (Product product);
    List<Product> getAllProducts();
    Optional<Product>getProductsById(Long id);

    Optional<Product>updateProduct(Long id,Product product);
}
