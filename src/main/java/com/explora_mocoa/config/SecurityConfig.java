package com.explora_mocoa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * CONFIGURACIÓN GENERAL DE SEGURIDAD
 *
 * Esta clase es el "portero" de la aplicación. Cada petición que llega
 * pasa primero por aquí antes de llegar a cualquier controller.
 *
 * Aquí solo van las reglas GENERALES. Las reglas por rol de cada módulo
 * (quién puede crear, editar o borrar) van en cada controller con @PreAuthorize.
 */
@Configuration
// Activa @PreAuthorize en los controllers. Sin esto, las anotaciones no hacen nada.
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF protege formularios web con sesión. Nuestra API es REST
            // (Postman / frontend), así que se desactiva.
            .csrf(csrf -> csrf.disable())

            // Permite que el frontend (otro puerto) llame a la API.
            // Usa los @CrossOrigin que ya tienen los controllers.
            .cors(Customizer.withDefaults())

            // STATELESS = el servidor no guarda sesión. En cada petición
            // el usuario debe mandar sus credenciales (ahora con HTTP Basic,
            // la otra semana con el token JWT).
            .sessionManagement(s ->
                    s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
                // 1. Registro: cualquiera puede crear su cuenta (queda como TURISTA).
                .requestMatchers(HttpMethod.POST, "/api/auth/registro").permitAll()

                // 2. Catálogo público: cualquiera puede VER (GET) sin iniciar sesión.
                .requestMatchers(HttpMethod.GET,
                        "/api/lugares/**",
                        "/api/eventos/**",
                        "/api/comidas/**",
                        "/api/servicios/**",
                        "/api/categorias/**",
                        "/api/resenas/**").permitAll()

                // 3. RED DE SEGURIDAD: todo lo demás exige haber iniciado sesión.
                //    Si un controller olvida su @PreAuthorize, la ruta igual
                //    queda protegida (aunque no por rol).
                .anyRequest().authenticated()
            )

            // HTTP Basic: el usuario manda correo y contraseña en cada petición.
            // Es temporal para la primera entrega. La otra semana se cambia por JWT.
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * BCrypt convierte la contraseña en un "hash" (texto ilegible) antes de
     * guardarla. Así, si alguien ve la base de datos, no ve las contraseñas.
     * Ejemplo: "admin123" se guarda como "$2a$10$Xk3...".
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}