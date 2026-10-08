package com.explora_mocoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.explora_mocoa.entities.Evento;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByLugarTuristicoId(Long lugarTuristicoId);
    List<Evento> findByUsuarioId(Long usuarioId);
}
