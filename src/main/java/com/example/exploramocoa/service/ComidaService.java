package com.example.exploramocoa.service;
 
import com.example.exploramocoa.entity.Comida;
import com.example.exploramocoa.repository.ComidaRepository;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class ComidaService {
 
    private final ComidaRepository comidaRepository;

    ComidaService(ComidaRepository comidaRepository) {
        this.comidaRepository = comidaRepository;
    }
 
    public List<Comida> listarTodas() {
        return comidaRepository.findAll();
    }
 
    public Comida buscarPorId(Long id) {
        return comidaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comida no encontrada con id " + id));
    }
 
    public Comida crear(Comida comida) {
        return comidaRepository.save(comida);
    }
 
    public Comida actualizar(Long id, Comida datos) {
        Comida existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setTipo(datos.getTipo());
        return comidaRepository.save(existente);
    }
 
    public void eliminar(Long id) {
        buscarPorId(id);
        comidaRepository.deleteById(id);
    }
}
