package com.example.formativa2.repositorio;

import com.example.formativa2.entidades.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repositorioUsuarios extends JpaRepository<usuarios, Long> {
}
