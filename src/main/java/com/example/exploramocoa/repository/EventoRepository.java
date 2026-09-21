package com.example.exploramocoa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exploramocoa.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCategoria(String categoria);
   List<Evento> findByLugarTuristico_Nombre(String nombre); // Busca eventos usando el nombre de su lugar turístico.
    List<Evento> findByFechaAfter(LocalDateTime fecha);
    List<Evento> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
