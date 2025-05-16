package com.example.formativa2.repositorio;

import com.example.formativa2.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface repositorioPrestamos extends JpaRepository<prestamos, Long>{
    List<prestamos> findByUsuario(usuarios usuario);


    List<prestamos> findByLibro(libros libro);


    List<prestamos> findByLibroAndEstado(libros libro, prestamos.EstadoPrestamo estado);


    List<prestamos> findByUsuarioAndEstado(usuarios usuario, prestamos.EstadoPrestamo estado);

    List<prestamos> findByFechaDevolucionPrevistaBeforeAndEstado(LocalDate fecha, prestamos.EstadoPrestamo estado);
}
