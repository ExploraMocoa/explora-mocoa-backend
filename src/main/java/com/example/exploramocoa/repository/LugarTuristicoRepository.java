package com.example.exploramocoa.repository;
 
import com.example.exploramocoa.entity.LugarTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface LugarTuristicoRepository extends JpaRepository<LugarTuristico, Long> {
    java.util.List<LugarTuristico> findByCategoria(String categoria);
}
