package com.example.exploramocoa.service;

import com.example.exploramocoa.entity.ItemCarrito;
import com.example.exploramocoa.repository.ItemCarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarritoService {

    private final ItemCarritoRepository itemCarritoRepository;

    public ItemCarritoService(ItemCarritoRepository itemCarritoRepository) {
        this.itemCarritoRepository = itemCarritoRepository;
    }

    public List<ItemCarrito> listarTodos() {
        return itemCarritoRepository.findAll();
    }

    public ItemCarrito buscarPorId(Long id) {
        return itemCarritoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Ítem de carrito no encontrado con id " + id
                        )
                );
    }

    public ItemCarrito crear(ItemCarrito item) {
        return itemCarritoRepository.save(item);
    }

    public ItemCarrito actualizar(Long id, ItemCarrito datos) {
        ItemCarrito existente = buscarPorId(id);

        existente.setNombreProducto(datos.getNombreProducto());
        existente.setCantidad(datos.getCantidad());
        existente.setPrecioUnitario(datos.getPrecioUnitario());

        return itemCarritoRepository.save(existente);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        itemCarritoRepository.deleteById(id);
    }
}