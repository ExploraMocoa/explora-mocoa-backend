package com.explora_mocoa.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.explora_mocoa.entities.Servicio;
import com.explora_mocoa.services.ServicioService;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {

    private final ServicioService service;

    public ServicioController(ServicioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Servicio> listar(@RequestParam(required = false) Long lugarId) {
        if (lugarId != null) {
            return service.findByLugar(lugarId);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Servicio obtener(@PathVariable Long id) {
        return service.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVEEDOR')")
    @PostMapping
    public ResponseEntity<Servicio> crear(@Valid @RequestBody Servicio servicio) {
        return new ResponseEntity<>(service.create(servicio), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVEEDOR')")
    @PutMapping("/{id}")
    public Servicio actualizar(@PathVariable Long id, @RequestBody Servicio servicio) {
        return service.update(id, servicio);
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROVEEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}