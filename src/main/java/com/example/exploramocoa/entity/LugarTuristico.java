package com.example.exploramocoa.entity;
 
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore; // Evita ciclos infinitos al responder datos de la API.
import lombok.EqualsAndHashCode; // Permite excluir la lista de eventos de la comparación de objetos.
import lombok.ToString; // Permite excluir la lista de eventos al mostrar el objeto como texto.
import java.util.ArrayList; // Permite iniciar una lista vacía de eventos.
import java.util.List; // Define el tipo de la lista de eventos.

 
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

@JsonIgnore // Evita enviar lugar → eventos → lugar repetidamente en JSON.
@ToString.Exclude // Evita ciclos al convertir el objeto a texto.
@EqualsAndHashCode.Exclude // Evita ciclos al comparar objetos.
@OneToMany(mappedBy = "lugarTuristico") // Un lugar turístico puede tener muchos eventos.
private List<Evento> eventos = new ArrayList<>(); // Lista inicialmente vacía de eventos del lugar.

@ManyToOne(optional = false) // Muchos lugares turísticos pueden haber sido creados por un usuario.
@JoinColumn(name = "creador_id", nullable = false) // Crea creador_id como clave foránea hacia usuarios.id.
private Usuario creador; // Guarda el usuario que publicó este lugar turístico.

}
