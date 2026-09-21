package com.example.exploramocoa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "items_carrito")
@Data
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    private Double precioUnitario;

    @ManyToOne(optional = false)
@JoinColumn(name = "usuario_id", nullable = false)
private Usuario usuario;

@ManyToOne(optional = false) // Muchos ítems del carrito pueden corresponder a una misma comida.
@JoinColumn(name = "comida_id", nullable = false) // Crea la columna comida_id y su clave foránea hacia comidas.id.
private Comida comida; // Guarda la comida real que el usuario agregó al carrito.


}