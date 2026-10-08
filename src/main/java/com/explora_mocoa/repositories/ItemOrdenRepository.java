package com.explora_mocoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.explora_mocoa.entities.ItemOrden;

import java.util.List;

@Repository
public interface ItemOrdenRepository extends JpaRepository<ItemOrden, Long> {
    List<ItemOrden> findByOrdenId(Long ordenId);
    List<ItemOrden> findByComidaId(Long comidaId);
}
