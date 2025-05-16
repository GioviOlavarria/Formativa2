package com.example.formativa2.entidades;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_libro;

    @Column(nullable = false)
    private String nombreLibro;

    @Column(nullable = false)
    private String autor;

    @Column
    private String descripcionLibro;

    @Column(nullable = false)
    private boolean disponible = true;


}
