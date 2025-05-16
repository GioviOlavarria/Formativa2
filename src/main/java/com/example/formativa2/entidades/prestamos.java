package com.example.formativa2.entidades;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prestamos")
public class prestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prestamo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonBackReference("usuario-prestamos")
    private usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private libros libro;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column
    private LocalDate fechaDevolucionPrevista;

    @Column
    private LocalDate fechaDevolucionReal;

    @Column
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    public enum EstadoPrestamo {
        PRESTADO,
        DEVUELTO,
        VENCIDO
    }
}