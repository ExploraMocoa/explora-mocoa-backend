package com.example.exploramocoa.controller;

import com.example.exploramocoa.entity.Evento;
import com.example.exploramocoa.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(eventoService.listarTodos());
    }

    @GetMapping("/proximos")
    public ResponseEntity<List<Evento>> listarProximos() {
        return ResponseEntity.ok(eventoService.listarProximos());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Evento>> porCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(eventoService.buscarPorCategoria(categoria));
    }

    @GetMapping("/lugar/{lugar}")
    public ResponseEntity<List<Evento>> porLugar(@PathVariable String lugar) {
        return ResponseEntity.ok(eventoService.buscarPorLugar(lugar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Evento> crear(@Valid @RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.crear(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizar(@PathVariable Long id, @Valid @RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.actualizar(id, evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
