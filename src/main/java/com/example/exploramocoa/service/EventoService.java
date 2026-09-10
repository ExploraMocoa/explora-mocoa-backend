package com.example.exploramocoa.service;

import com.example.exploramocoa.entity.Evento;
import com.example.exploramocoa.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento no encontrado con id " + id));
    }

    public List<Evento> buscarPorCategoria(String categoria) {
        return eventoRepository.findByCategoria(categoria);
    }

    public List<Evento> buscarPorLugar(String lugar) {
        return eventoRepository.findByLugar(lugar);
    }

    public List<Evento> listarProximos() {
        return eventoRepository.findByFechaAfter(LocalDateTime.now());
    }

    public Evento crear(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento actualizar(Long id, Evento datos) {
        Evento existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setFecha(datos.getFecha());
        existente.setLugar(datos.getLugar());
        existente.setCategoria(datos.getCategoria());
        return eventoRepository.save(existente);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        eventoRepository.deleteById(id);
    }
}
