package com.example.formativa2.repositorio;

import com.example.formativa2.entidades.libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repositorioLibros extends JpaRepository<libros, Long> {
}
