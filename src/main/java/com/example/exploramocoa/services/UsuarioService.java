package com.example.exploramocoa.services;

import com.example.exploramocoa.models.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> obtenerTodos() {
        return usuarios;
    }
}
