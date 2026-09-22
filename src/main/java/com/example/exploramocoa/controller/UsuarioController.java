package com.example.exploramocoa.controller;
import com.example.exploramocoa.entity.Usuario;
import com.example.exploramocoa.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
    
@PostMapping
public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario) {
    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(usuario));
}
    
  @PutMapping("/{id}")
public ResponseEntity<Usuario> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody Usuario usuario) {
    return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
}

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}