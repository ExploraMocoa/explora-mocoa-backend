package com.example.exploramocoa.repository;
 
import com.example.exploramocoa.entity.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface ComidaRepository extends JpaRepository<Comida, Long> {
    java.util.List<Comida> findByTipo(String tipo);
}
