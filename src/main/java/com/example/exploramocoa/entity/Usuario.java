package com.example.exploramocoa.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode; // Permite excluir campos al comparar objetos.
import lombok.ToString; // Permite excluir campos al convertir objetos a texto.

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El correo es obligatorio")
    
    @Email(message = "El correo no tiene un formato válido")
    
    @Column(unique = true)
    private String correo;
    
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    @JsonIgnore
@OneToMany(mappedBy = "usuario")
private List<ItemCarrito> itemsCarrito = new ArrayList<>();

@JsonIgnore // Evita que al consultar un usuario se repitan sus lugares y el creador infinitamente.
@ToString.Exclude // Evita ciclos al convertir el usuario a texto.
@EqualsAndHashCode.Exclude // Evita ciclos al comparar objetos.
@OneToMany(mappedBy = "creador") // Un usuario puede crear muchos lugares turísticos.
private List<LugarTuristico> lugaresTuristicosCreados = new ArrayList<>(); // Lista de lugares publicados por el usuario.

@JsonIgnore // Evita que al consultar un usuario se repitan sus eventos y el creador infinitamente.
@ToString.Exclude // Evita ciclos al convertir el usuario a texto.
@EqualsAndHashCode.Exclude // Evita ciclos al comparar objetos.
@OneToMany(mappedBy = "creador") // Un usuario puede crear muchos eventos.
private List<Evento> eventosCreados = new ArrayList<>(); // Lista de eventos publicados por el usuario.

}
