package com.prueba.iuvity.pruebaiuvity.security.repository;

import java.util.Optional;


import com.prueba.iuvity.pruebaiuvity.security.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Long> {

	public Optional<Rol> findByNombre(String nombre);

}
