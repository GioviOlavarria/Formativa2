package com.example.formativa2.servicio;

import com.example.formativa2.entidades.usuarios;
import com.example.formativa2.repositorio.repositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class servicioUsuarios {
    @Autowired
    repositorioUsuarios repositorioUsuarios;

    public List<usuarios> listarUsuarios(){

        return repositorioUsuarios.findAll();
    }

    public Optional<usuarios> listarUsuarios(Long id_usuario){

        return repositorioUsuarios.findById(id_usuario);
    }

    public void guardarOActualizar(usuarios usuarios){

        repositorioUsuarios.save(usuarios);
    }
    public void borrar(Long id_usuario){

        repositorioUsuarios.deleteById(id_usuario);
    }
}
