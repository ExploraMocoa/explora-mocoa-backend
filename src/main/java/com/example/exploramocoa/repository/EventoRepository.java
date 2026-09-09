package com.example.exploramocoa.repository;

import com.example.exploramocoa.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCategoria(String categoria);
    List<Evento> findByLugar(String lugar);
    List<Evento> findByFechaAfter(LocalDateTime fecha);
    List<Evento> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
