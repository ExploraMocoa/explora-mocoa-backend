package com.example.exploramocoa.controller;

import com.example.exploramocoa.entity.LugarTuristico;
import com.example.exploramocoa.service.LugarTuristicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lugares-turisticos")
public class LugarTuristicoController {

    private final LugarTuristicoService lugarTuristicoService;

    public LugarTuristicoController(LugarTuristicoService lugarTuristicoService) {
        this.lugarTuristicoService = lugarTuristicoService;
    }

    @GetMapping
    public ResponseEntity<List<LugarTuristico>> listar() {
        return ResponseEntity.ok(lugarTuristicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LugarTuristico> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(lugarTuristicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LugarTuristico> crear(
            @Valid @RequestBody LugarTuristico lugar) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lugarTuristicoService.crear(lugar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LugarTuristico> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody LugarTuristico lugar) {
        return ResponseEntity.ok(lugarTuristicoService.actualizar(id, lugar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        lugarTuristicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}