package com.example.exploramocoa.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

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
}
