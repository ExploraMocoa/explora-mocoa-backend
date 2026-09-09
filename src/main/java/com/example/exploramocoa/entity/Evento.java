package com.example.exploramocoa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "eventos")
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar 500 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;

    @NotBlank(message = "El lugar es obligatorio")
    private String lugar;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;
}
