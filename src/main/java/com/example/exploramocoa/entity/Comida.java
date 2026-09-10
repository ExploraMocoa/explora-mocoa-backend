package com.example.exploramocoa.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
 
@Entity
@Table(name = "comidas")
@Data
public class Comida {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
 
    @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
    private String descripcion;
 
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;
 
    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;
}
