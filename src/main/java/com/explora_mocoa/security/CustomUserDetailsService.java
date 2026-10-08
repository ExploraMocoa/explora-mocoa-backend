package com.explora_mocoa.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.explora_mocoa.entities.Usuario;
import com.explora_mocoa.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * PUENTE ENTRE NUESTRA BASE DE DATOS Y SPRING SECURITY
 *
 * Spring Security no conoce nuestra tabla "usuarios". Cuando alguien
 * intenta entrar, Spring llama a loadUserByUsername(correo) y esta clase
 * le responde: "este es el usuario, esta es su contraseña encriptada y
 * este es su rol".
 *
 * Luego Spring compara la contraseña que mandó la persona con la
 * guardada (usando BCrypt) y, si coinciden, la deja pasar.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    // El rol se carga "lazy" (solo cuando se pide), por eso se necesita la transacción
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // 1. Buscar el usuario por correo (en nuestra app el "username" es el correo)
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Correo no registrado: " + correo));

        // 2. Convertir su rol al formato de Spring: "ADMIN" -> "ROLE_ADMIN".
        //    Spring exige el prefijo ROLE_ para que funcione hasRole('ADMIN').
        //    Si el usuario no tiene rol, entra sin permisos especiales.
        List<SimpleGrantedAuthority> permisos = new ArrayList<>();
        if (usuario.getRol() != null) {
            String nombreRol = usuario.getRol().getNombre();
            permisos.add(new SimpleGrantedAuthority("ROLE_" + nombreRol));
        }

        // 3. Devolver el usuario en el formato que Spring entiende
        return User.withUsername(usuario.getCorreo())
                .password(usuario.getContrasena()) // ya viene encriptada con BCrypt
                .authorities(permisos)
                .build();
    }
}