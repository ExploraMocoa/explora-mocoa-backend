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

 @ManyToOne(optional = false) // Muchos eventos pueden pertenecer a un solo lugar turístico.
@JoinColumn(name = "lugar_turistico_id", nullable = false) // Crea la columna y clave foránea hacia lugares_turisticos.id.
private LugarTuristico lugarTuristico; // Guarda el objeto LugarTuristico asociado al evento.

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

@ManyToOne(optional = false) // Muchos eventos pueden haber sido creados por un usuario.
@JoinColumn(name = "creador_id", nullable = false) // Crea creador_id como clave foránea hacia usuarios.id.
private Usuario creador; // Guarda el usuario que publicó este evento.

}
