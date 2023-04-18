package com.prueba.iuvity.pruebaiuvity.security.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JWTAuthResonseDTO {

	private String tokenDeAcceso;
		private String tipoDeToken = "Bearer";
	private String nombreUsuario;
	private Collection<? extends GrantedAuthority> authorities;

	public JWTAuthResonseDTO(String tokenDeAcceso) {
		super();
		this.tokenDeAcceso = tokenDeAcceso;
	}

	public JWTAuthResonseDTO(String tokenDeAcceso, String nombreUsuario,
			Collection<? extends GrantedAuthority> authorities) {
		this.tokenDeAcceso = tokenDeAcceso;
		this.nombreUsuario = nombreUsuario;
		this.authorities = authorities;
	}

	public String getTokenDeAcceso() {
		return tokenDeAcceso;
	}

	public void setTokenDeAcceso(String tokenDeAcceso) {
		this.tokenDeAcceso = tokenDeAcceso;
	}

	public String getTipoDeToken() {
		return tipoDeToken;
	}

	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
