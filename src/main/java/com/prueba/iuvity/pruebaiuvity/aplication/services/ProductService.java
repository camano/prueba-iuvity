package com.prueba.iuvity.pruebaiuvity.aplication.services;

import com.prueba.iuvity.pruebaiuvity.domain.models.Product;
import com.prueba.iuvity.pruebaiuvity.domain.ports.in.ProductUseCase;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

public class ProductService implements ProductUseCase {

    private final ProductUseCase productUseCase;

    public ProductService(@Lazy ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @Override
    public Product createProduct(Product product) {
        return productUseCase.createProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productUseCase.getAllProducts();
    }

    @Override
    public Optional<Product> getProductsById(Long id) {
        return productUseCase.getProductsById(id);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        return productUseCase.updateProduct(id,product);
    }
}
