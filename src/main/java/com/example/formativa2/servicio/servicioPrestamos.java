package com.example.formativa2.servicio;

import com.example.formativa2.entidades.*;
import com.example.formativa2.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class servicioPrestamos {
    @Autowired
    repositorioPrestamos repositorioPrestamos;

    @Autowired
    repositorioLibros repositorioLibros;

    @Autowired
    repositorioUsuarios repositorioUsuarios;

    public List<prestamos> listarPrestamos() {
        return repositorioPrestamos.findAll();
    }

    public Optional<prestamos> listarPrestamo(Long id_prestamo) {
        return repositorioPrestamos.findById(id_prestamo);
    }

    public void guardarOActualizar(prestamos prestamo){
        repositorioPrestamos.save(prestamo);
    }

    public void borrar(Long id_prestamo) {
        repositorioPrestamos.deleteById(id_prestamo);
    }

    public prestamos crearPrestamo(Long id_usuario, Long id_libro, int diasPrestamo) {
        usuarios usuario = repositorioUsuarios.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        libros libro = repositorioLibros.findById(id_libro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        if (!libro.isDisponible()) {
            throw new RuntimeException("Libro no disponible " + libro.getNombreLibro());
        }

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucionPrevista = fechaPrestamo.plusDays(diasPrestamo);

        prestamos prestamo = new prestamos();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucionPrevista(fechaDevolucionPrevista);
        prestamo.setEstado(prestamos.EstadoPrestamo.PRESTADO);

        libro.setDisponible(false);
        repositorioLibros.save(libro);

        return repositorioPrestamos.save(prestamo);
    }

    public prestamos devolverLibro(Long id_prestamo) {
        prestamos prestamo = repositorioPrestamos.findById(id_prestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if (prestamo.getEstado() == prestamos.EstadoPrestamo.DEVUELTO) {
            throw new RuntimeException("El libro ya ha sido devuelto");
        }

        libros libro = prestamo.getLibro();
        libro.setDisponible(true);
        repositorioLibros.save(libro);

        prestamo.setEstado(prestamos.EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucionReal(LocalDate.now());

        return repositorioPrestamos.save(prestamo);
    }

    public List<prestamos> listarPrestamosPorUsuario(Long id_usuario) {
        usuarios usuario = repositorioUsuarios.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return repositorioPrestamos.findByUsuario(usuario);
    }

    public List<prestamos> listarPrestamosPorLibro(Long id_libro) {
        libros libro = repositorioLibros.findById(id_libro)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return repositorioPrestamos.findByLibro(libro);
    }

    public void actualizarPrestamosSobreDiaDeVencimiento(){
        List<prestamos> prestamosVencidos = repositorioPrestamos.findByFechaDevolucionPrevistaBeforeAndEstado(
                LocalDate.now(), prestamos.EstadoPrestamo.PRESTADO);

        for (prestamos prestamo : prestamosVencidos) {
            prestamo.setEstado(prestamos.EstadoPrestamo.VENCIDO);
            repositorioPrestamos.save(prestamo);
        }
    }
}