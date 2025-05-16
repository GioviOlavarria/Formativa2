package com.example.formativa2.controlador;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.formativa2.entidades.libros;
import com.example.formativa2.servicio.servicioLibros;


@RestController
@RequestMapping(path = "api/v1/libros")
public class controladorLibros {

    @Autowired
    private final servicioLibros servicioLibros;

    public controladorLibros(servicioLibros servicioLibros) {
        this.servicioLibros = servicioLibros;
    }
    @GetMapping
    public List<libros> getAll(){
        return servicioLibros.listarLibros();
    }
    @GetMapping("/{id_libro}")
    public Optional<libros> getBId(@PathVariable("id_libro") Long id_libro){
        return servicioLibros.listarLibros(id_libro);
    }
    @PostMapping
    public void guardarActualizar(@RequestBody libros libros){
        servicioLibros.guardarOActualizar(libros);
    }
    @DeleteMapping("/{id_libro}")
    public void delete(@PathVariable("id_libro") Long id_libro){
        servicioLibros.borrar(id_libro);
    }
}


