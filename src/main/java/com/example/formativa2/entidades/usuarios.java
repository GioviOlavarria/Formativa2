package com.example.formativa2.entidades;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity

@Table(name = "tabla_usuarios")
public class usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String primerNombre;

    private String primerApellido;

    @Column(name = "correo_electronico", unique = true, nullable = false)
    private String email;
}
