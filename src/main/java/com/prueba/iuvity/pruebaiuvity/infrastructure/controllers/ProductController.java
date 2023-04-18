package com.prueba.iuvity.pruebaiuvity.infrastructure.controllers;

import com.prueba.iuvity.pruebaiuvity.aplication.services.ProductService;
import com.prueba.iuvity.pruebaiuvity.domain.models.Product;
import com.prueba.iuvity.pruebaiuvity.domain.models.UsuarioModel;
import com.prueba.iuvity.pruebaiuvity.infrastructure.exception.ProductException;
import com.prueba.iuvity.pruebaiuvity.infrastructure.exception.UsuarioException;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.UsuarioMapper;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import com.prueba.iuvity.pruebaiuvity.security.jwt.JwtTokenProvider;
import com.prueba.iuvity.pruebaiuvity.security.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public ProductController(@Lazy ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, @RequestHeader("Authorization") String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
            token =token.substring(7, token.length());
        }
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Usuario usuario = usuarioRepositorio.findByUsername(username).orElse(null);
        product.setUsuario(UsuarioMapper.toDomainModelUsuario(usuario));
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.ACCEPTED);

    }


    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        System.out.println("variable "+productId);
        return productService.getProductsById(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product producto,@RequestHeader("Authorization") String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
            token =token.substring(7, token.length());
        }
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Usuario usuario = usuarioRepositorio.findByUsername(username).orElse(null);
        producto.setUsuario(UsuarioMapper.toDomainModelUsuario(usuario));
        return productService.updateProduct(productId, producto).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseThrow(()-> new ProductException(HttpStatus.BAD_REQUEST, "Hubo un error al editar el producto"));
    }
}
