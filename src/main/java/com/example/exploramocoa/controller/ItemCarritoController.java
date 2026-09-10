package com.example.exploramocoa.controller;

import com.example.exploramocoa.entity.ItemCarrito;
import com.example.exploramocoa.service.ItemCarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class ItemCarritoController {

    private final ItemCarritoService itemCarritoService;

    public ItemCarritoController(ItemCarritoService itemCarritoService) {
        this.itemCarritoService = itemCarritoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemCarrito>> listar() {
        return ResponseEntity.ok(itemCarritoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCarrito> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(itemCarritoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemCarrito> crear(@RequestBody ItemCarrito item) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemCarritoService.crear(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCarrito> actualizar(
            @PathVariable Long id,
            @RequestBody ItemCarrito item
    ) {
        return ResponseEntity.ok(
                itemCarritoService.actualizar(id, item)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        itemCarritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}