package com.explora_mocoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.explora_mocoa.entities.Comida;

import java.util.List;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Long> {
    List<Comida> findByTipo(String tipo);
    List<Comida> findByNombreContainingIgnoreCase(String nombre);
}
