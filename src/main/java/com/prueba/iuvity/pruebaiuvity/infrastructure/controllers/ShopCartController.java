package com.prueba.iuvity.pruebaiuvity.infrastructure.controllers;

import com.prueba.iuvity.pruebaiuvity.aplication.services.ShopCartService;
import com.prueba.iuvity.pruebaiuvity.domain.models.ShopCart;
import com.prueba.iuvity.pruebaiuvity.domain.models.UsuarioModel;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.ShopCartMapper;
import com.prueba.iuvity.pruebaiuvity.infrastructure.mapper.UsuarioMapper;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import com.prueba.iuvity.pruebaiuvity.security.jwt.JwtTokenProvider;
import com.prueba.iuvity.pruebaiuvity.security.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopCart")
public class ShopCartController {

    private final ShopCartService shopCartService;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public ShopCartController(@Lazy ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @PostMapping
    public ResponseEntity<?> addShopCart(@RequestBody ShopCart shopCart,@RequestHeader("Authorization") String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
            token =token.substring(7, token.length());
        }
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Usuario usuario = usuarioRepositorio.findByUsername(username).orElse(null);
        shopCart.setUsuario(UsuarioMapper.toDomainModelUsuario(usuario));

        return new ResponseEntity<>(shopCartService.addCart(shopCart), HttpStatus.CREATED);
    }


    @GetMapping("/shopUsuario")
    public ResponseEntity<?> cardUsuario(@RequestHeader("Authorization") String token) {

        if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
            token =token.substring(7, token.length());
        }
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Usuario usuario = usuarioRepositorio.findByUsername(username).orElse(null);
        return new ResponseEntity<>(shopCartService.getCartUsuario(usuario), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{shopId}")
    public ResponseEntity<?> deleteShopCart(@PathVariable Long shopId) {
        if (shopCartService.deleteCart(shopId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
