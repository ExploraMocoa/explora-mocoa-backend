package com.example.exploramocoa.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

 import com.fasterxml.jackson.annotation.JsonIgnore; // Evita ciclos infinitos al devolver datos JSON.
import lombok.EqualsAndHashCode; // Permite excluir la lista de comparaciones automáticas de Lombok.
import lombok.ToString; // Permite excluir la lista de conversiones automáticas a texto.
import java.util.ArrayList; // Permite iniciar la lista vacía.
import java.util.List; // Representa una colección de ítems del carrito.
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

 @JsonIgnore // Evita que Comida devuelva sus ítems y se repita el JSON.
@ToString.Exclude // Evita ciclos al mostrar el objeto como texto.
@EqualsAndHashCode.Exclude // Evita ciclos al comparar objetos.
@OneToMany(mappedBy = "comida") // Una comida puede aparecer en muchos ítems de carrito.
private List<ItemCarrito> itemsCarrito = new ArrayList<>(); // Lista inicialmente vacía de ítems asociados.
}
