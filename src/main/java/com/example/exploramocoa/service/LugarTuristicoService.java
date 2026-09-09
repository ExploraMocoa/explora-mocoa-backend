package com.example.exploramocoa.service;
 
import com.example.exploramocoa.entity.LugarTuristico;
import com.example.exploramocoa.repository.LugarTuristicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class LugarTuristicoService {
 
    @Autowired
    private LugarTuristicoRepository lugarTuristicoRepository;
 
    public List<LugarTuristico> listarTodos() {
        return lugarTuristicoRepository.findAll();
    }
 
    public LugarTuristico buscarPorId(Long id) {
        return lugarTuristicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lugar turístico no encontrado con id " + id));
    }
 
    public LugarTuristico crear(LugarTuristico lugar) {
        return lugarTuristicoRepository.save(lugar);
    }
 
    public LugarTuristico actualizar(Long id, LugarTuristico datos) {
        LugarTuristico existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setUbicacion(datos.getUbicacion());
        existente.setCategoria(datos.getCategoria());
        return lugarTuristicoRepository.save(existente);
    }
 
    public void eliminar(Long id) {
        buscarPorId(id);
        lugarTuristicoRepository.deleteById(id);
    }
}
