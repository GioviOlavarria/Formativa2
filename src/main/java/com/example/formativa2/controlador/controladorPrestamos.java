package com.example.formativa2.controlador;

import com.example.formativa2.entidades.prestamos;
import com.example.formativa2.servicio.servicioPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prestamos")
public class controladorPrestamos {

    @Autowired
    private servicioPrestamos servicioPrestamos;

    @GetMapping
    public List<prestamos> obtenerTodos() {
        return servicioPrestamos.listarPrestamos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<prestamos> obtenerPorId(@PathVariable Long id) {
        Optional<prestamos> prestamo = servicioPrestamos.listarPrestamo(id);
        return prestamo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{id_usuario}")
    public List<prestamos> obtenerPorUsuario(@PathVariable Long id_usuario) {
        return servicioPrestamos.listarPrestamosPorUsuario(id_usuario);
    }

    @GetMapping("/libros/{id_libro}")
    public List<prestamos> obtenerPorLibro(@PathVariable Long id_libro) {
        return servicioPrestamos.listarPrestamosPorLibro(id_libro);
    }

    @PostMapping("/prestar")
    public ResponseEntity<?> crearPrestamo(@RequestParam Long id_usuario,
                                           @RequestParam Long id_libro,
                                           @RequestParam(defaultValue = "14") int diasPrestamo) {
        try {
            prestamos nuevoPrestamo = servicioPrestamos.crearPrestamo(id_usuario, id_libro, diasPrestamo);
            return new ResponseEntity<>(nuevoPrestamo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<?> devolverLibro(@PathVariable Long id) {
        try {
            prestamos prestamo = servicioPrestamos.devolverLibro(id);
            return ResponseEntity.ok(prestamo);
        } catch (RuntimeException e) {
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar-vencidos")
    public ResponseEntity<?> actualizarVencidos() {
        servicioPrestamos.actualizarPrestamosSobreDiaDeVencimiento();
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Préstamos vencidos actualizados correctamente");
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPrestamo(@PathVariable Long id) {
        try {
            servicioPrestamos.borrar(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Préstamo eliminado correctamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("error", "No se pudo eliminar el préstamo");
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}