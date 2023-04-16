package com.prueba.iuvity.pruebaiuvity.security.repository;

import java.util.Optional;


import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);

	public Optional<Usuario> findByUsernameOrEmail(String username, String email);

	public Optional<Usuario> findByUsername(String username);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);

}
