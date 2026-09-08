package com.example.exploramocoa.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
 
@Entity
@Table(name = "lugares_turisticos")
@Data
public class LugarTuristico {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
 
    @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
    private String descripcion;
 
    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;
 
    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;
}
