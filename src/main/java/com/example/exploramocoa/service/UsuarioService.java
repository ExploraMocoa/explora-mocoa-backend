package com.example.exploramocoa.service;
import com.example.exploramocoa.entity.Usuario;
import com.example.exploramocoa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }
    
    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }
        return usuarioRepository.save(usuario);
    }
    
    public Usuario actualizar(Long id, Usuario datos) {
        Usuario existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setCorreo(datos.getCorreo());
        existente.setContrasena(datos.getContrasena());
        return usuarioRepository.save(existente);
    }
    
    public void eliminar(Long id) {
        buscarPorId(id);
        usuarioRepository.deleteById(id);
    }
}
