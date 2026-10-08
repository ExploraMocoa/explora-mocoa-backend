package com.explora_mocoa.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.explora_mocoa.config.DataInitializer;
import com.explora_mocoa.entities.Rol;
import com.explora_mocoa.entities.Usuario;
import com.explora_mocoa.repositories.RolRepository;
import com.explora_mocoa.services.UsuarioService;

import java.util.Map;

/**
 * REGISTRO Y DATOS DEL USUARIO ACTUAL
 *
 * La otra semana aquí se agrega POST /api/auth/login, que devolverá el token JWT.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    public AuthController(UsuarioService usuarioService, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
    }

    /**
     * Registro público (no necesita iniciar sesión).
     * SIEMPRE crea el usuario como TURISTA, aunque en el JSON manden otro rol.
     * Así nadie puede registrarse a sí mismo como ADMIN.
     */
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registro(@Valid @RequestBody Usuario usuario) {
        Rol turista = rolRepository.findByNombre(DataInitializer.TURISTA)
                .orElseThrow(() -> new IllegalStateException("El rol TURISTA no existe"));
        usuario.setRol(turista); // se ignora cualquier rol que venga en la petición
        return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.CREATED);
    }

    /**
     * Devuelve quién soy y qué rol tengo. Sirve para probar que el login funciona.
     * Necesita sesión iniciada (lo cubre la "red de seguridad" de SecurityConfig).
     */
    @GetMapping("/yo")
    public Map<String, Object> yo(Authentication authentication) {
        return Map.of(
                "correo", authentication.getName(),
                "roles", authentication.getAuthorities()
        );
    }
}