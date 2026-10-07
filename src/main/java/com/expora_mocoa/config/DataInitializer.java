package com.expora_mocoa.config;

import com.expora_mocoa.entities.Rol;
import com.expora_mocoa.entities.Usuario;
import com.expora_mocoa.repositories.RolRepository;
import com.expora_mocoa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * DATOS INICIALES
 *
 * CommandLineRunner = código que Spring ejecuta UNA VEZ cada vez que
 * arranca la aplicación.
 *
 * Crea los 3 roles y un usuario ADMIN si todavía no existen. Sin esto no
 * habría ningún administrador y nadie podría crear usuarios ni roles.
 * Si ya existen, no hace nada (no duplica).
 */
@Component
public class DataInitializer implements CommandLineRunner {

    public static final String ADMIN = "ADMIN";
    public static final String PROVEEDOR = "PROVEEDOR";
    public static final String TURISTA = "TURISTA";

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Se leen de application.properties. Si no están, usa los valores después de ":"
    @Value("${app.admin.correo:admin@exploramocoa.com}")
    private String adminCorreo;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    public DataInitializer(RolRepository rolRepository,
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // 1. Crear los roles si no existen
        Rol rolAdmin = crearRolSiNoExiste(ADMIN);
        crearRolSiNoExiste(PROVEEDOR);
        crearRolSiNoExiste(TURISTA);

        // 2. Crear el usuario administrador si no existe
        if (!usuarioRepository.existsByCorreo(adminCorreo)) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setCorreo(adminCorreo);
            // La contraseña se guarda encriptada con BCrypt
            admin.setContrasena(passwordEncoder.encode(adminPassword));
            admin.setRol(rolAdmin);
            usuarioRepository.save(admin);
            System.out.println(">>> Usuario ADMIN creado: " + adminCorreo);
        }
    }

    private Rol crearRolSiNoExiste(String nombre) {
        return rolRepository.findByNombre(nombre)
                .orElseGet(() -> rolRepository.save(new Rol(nombre)));
    }
}