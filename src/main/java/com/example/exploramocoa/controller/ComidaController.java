package com.example.exploramocoa.controller;
 
import com.example.exploramocoa.entity.Comida;
import com.example.exploramocoa.service.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/comidas")
public class ComidaController {
 
    @Autowired
    private ComidaService comidaService;
 
    @GetMapping
    public ResponseEntity<List<Comida>> listar() {
        return ResponseEntity.ok(comidaService.listarTodas());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Comida> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(comidaService.buscarPorId(id));
    }
 
    @PostMapping
    public ResponseEntity<Comida> crear(@RequestBody Comida comida) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comidaService.crear(comida));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Comida> actualizar(@PathVariable Long id, @RequestBody Comida comida) {
        return ResponseEntity.ok(comidaService.actualizar(id, comida));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comidaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
