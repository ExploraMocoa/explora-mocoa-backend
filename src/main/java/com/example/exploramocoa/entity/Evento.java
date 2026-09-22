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
    @Column(length = 500)
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;

    @NotNull(message = "El lugar turístico es obligatorio")
    @ManyToOne(optional = false)
    @JoinColumn(name = "lugar_turistico_id", nullable = false)
    private LugarTuristico lugarTuristico;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotNull(message = "El creador es obligatorio")
    @ManyToOne(optional = false)
    @JoinColumn(name = "creador_id", nullable = false)
    private Usuario creador;
}
