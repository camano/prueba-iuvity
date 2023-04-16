package com.prueba.iuvity.pruebaiuvity.security.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {


	private String jwtSecret = "JWTSecretKey";


	private int jwtExpirationInMs = 86400000;

	public String generarToken(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
		.collect(Collectors.toList());
		String token = Jwts.builder().setSubject(username).claim("roles", roles).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;
	}

	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {

		} catch (MalformedJwtException ex) {

		} catch (ExpiredJwtException ex) {

		} catch (UnsupportedJwtException ex) {

		} catch (IllegalArgumentException ex) {

		}
		return false;
	}
}
