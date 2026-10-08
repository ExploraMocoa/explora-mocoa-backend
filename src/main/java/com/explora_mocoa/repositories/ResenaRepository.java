package com.explora_mocoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.explora_mocoa.entities.Resena;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByLugarTuristicoId(Long lugarTuristicoId);
    List<Resena> findByUsuarioId(Long usuarioId);
}
