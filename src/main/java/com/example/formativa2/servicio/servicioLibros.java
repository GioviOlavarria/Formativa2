package com.example.formativa2.servicio;

import com.example.formativa2.entidades.libros;
import com.example.formativa2.repositorio.repositorioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class servicioLibros {
    @Autowired
    repositorioLibros repositorioLibros;

    public List<libros> listarLibros(){

        return repositorioLibros.findAll();
    }

    public Optional<libros> listarLibros(Long id_libro){

        return repositorioLibros.findById(id_libro);
    }

    public void guardarOActualizar(libros libros){

        repositorioLibros.save(libros);
    }
    public void borrar(Long id_libro){

        repositorioLibros.deleteById(id_libro);
    }
}
