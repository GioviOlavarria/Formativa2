package com.example.formativa2.entidades;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_libro;

    private String nombreLibro;

    private String autor;

    private String descripcionLibro;
}
