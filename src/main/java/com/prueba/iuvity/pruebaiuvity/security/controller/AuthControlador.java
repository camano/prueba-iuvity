package com.prueba.iuvity.pruebaiuvity.security.controller;

import java.util.HashSet;
import java.util.Set;


import com.prueba.iuvity.pruebaiuvity.security.dto.LoginDTO;
import com.prueba.iuvity.pruebaiuvity.security.dto.RegistroDTO;
import com.prueba.iuvity.pruebaiuvity.security.dto.TokenDto;
import com.prueba.iuvity.pruebaiuvity.security.entity.Rol;
import com.prueba.iuvity.pruebaiuvity.security.entity.Usuario;
import com.prueba.iuvity.pruebaiuvity.security.jwt.JwtTokenProvider;
import com.prueba.iuvity.pruebaiuvity.security.repository.RolRepositorio;
import com.prueba.iuvity.pruebaiuvity.security.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthControlador {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private RolRepositorio rolRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<TokenDto> authenticateUser(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);


	TokenDto tokenDto = new TokenDto(token);

		return new ResponseEntity<>(tokenDto, HttpStatus.OK);
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
		if (usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		if (usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

		Set<Rol> roles = new HashSet<>();
		roles.add(rolRepositorio.findByNombre("ROLE_USER").get());
		if (registroDTO.getRoles().contains("admin")) {
			roles.add(rolRepositorio.findByNombre("ROLE_ADMIN").get());
		}
		usuario.setRoles(roles);
		usuarioRepositorio.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
	}


	@GetMapping("/consultar-usuario")
	public ResponseEntity<?> consultarUsuario(@RequestHeader("Authorization") String token){
		Usuario usuario= new Usuario();
		if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
			token =token.substring(7, token.length());
			String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
			usuario = usuarioRepositorio.findByUsername(username).orElse(null);
		}

		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
}
