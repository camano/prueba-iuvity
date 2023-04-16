package com.prueba.iuvity.pruebaiuvity.aplication.usecases;

import com.prueba.iuvity.pruebaiuvity.domain.models.Product;
import com.prueba.iuvity.pruebaiuvity.domain.ports.in.ProductUseCase;
import com.prueba.iuvity.pruebaiuvity.domain.ports.out.ProductRepositoryPort;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Optional;

public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductUseCaseImpl(@Lazy ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.getAllProducts();
    }

    @Override
    public Optional<Product> getProductsById(Long id) {
        return productRepositoryPort.getProductsById(id);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepositoryPort.updateProduct(id,product);
    }
}
