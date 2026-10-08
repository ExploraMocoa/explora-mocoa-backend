package com.explora_mocoa.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.explora_mocoa.entities.Orden;
import com.explora_mocoa.services.OrdenService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
// Sobre la CLASE: todos los métodos son solo para TURISTA y ADMIN (nada público).
@PreAuthorize("hasAnyRole('TURISTA','ADMIN')")
public class OrdenController {

    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @GetMapping
    public List<Orden> listar(@RequestParam(required = false) Long usuarioId) {
        if (usuarioId != null) {
            return service.findByUsuario(usuarioId);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Orden obtener(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Orden> crear(@Valid @RequestBody Orden orden) {
        return new ResponseEntity<>(service.create(orden), HttpStatus.CREATED);
    }

    // Este método tiene su propia regla, que REEMPLAZA a la de la clase: solo ADMIN.
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/estado")
    public Orden cambiarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.cambiarEstado(id, body.get("estado"));
    }

    @PostMapping("/{id}/recalcular")
    public Orden recalcular(@PathVariable Long id) {
        return service.recalcularTotal(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}