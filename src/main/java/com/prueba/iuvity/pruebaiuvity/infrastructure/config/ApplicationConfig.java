package com.prueba.iuvity.pruebaiuvity.infrastructure.config;

import com.prueba.iuvity.pruebaiuvity.aplication.services.ProductService;
import com.prueba.iuvity.pruebaiuvity.aplication.services.ShopCartService;
import com.prueba.iuvity.pruebaiuvity.aplication.usecases.ProductUseCaseImpl;
import com.prueba.iuvity.pruebaiuvity.aplication.usecases.ShopUseCaseImpl;
import com.prueba.iuvity.pruebaiuvity.domain.ports.out.ProductRepositoryPort;
import com.prueba.iuvity.pruebaiuvity.domain.ports.out.ShopCartRepositoryPort;
import com.prueba.iuvity.pruebaiuvity.infrastructure.repositories.JpaProductRepositoryImpl;
import com.prueba.iuvity.pruebaiuvity.infrastructure.repositories.JpaShopCartRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {


    @Bean
    ProductService productService(ProductRepositoryPort productRepositoryPort){
        return new ProductService(new ProductUseCaseImpl(productRepositoryPort));
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(JpaProductRepositoryImpl jpaProductRepository){
        return jpaProductRepository;
    }

    @Bean
public ShopCartService shopCartService(ShopCartRepositoryPort shopCartRepositoryPort){
        return new ShopCartService(new ShopUseCaseImpl(shopCartRepositoryPort));
    }

    @Bean
    public ShopCartRepositoryPort shopCartRepositoryPort(JpaShopCartRepositoryImpl jpaShopCartRepository){
        return jpaShopCartRepository;
    }




}
