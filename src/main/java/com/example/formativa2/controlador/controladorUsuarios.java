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

import com.example.formativa2.entidades.usuarios;
import com.example.formativa2.servicio.servicioUsuarios;


@RestController
@RequestMapping(path = "api/v1/usuarios")
public class controladorUsuarios {

    @Autowired
    private final servicioUsuarios servicioUsuarios;

    public controladorUsuarios(servicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }
    @GetMapping
    public List<usuarios> getAll(){
        return servicioUsuarios.listarUsuarios();
    }
    @GetMapping("/{id_usuario}")
    public Optional<usuarios> getBId(@PathVariable("id_usuario") Long id_usuario){
        return servicioUsuarios.listarUsuarios(id_usuario);
    }
    @PostMapping
    public void guardarActualizar(@RequestBody usuarios usuarios){
        servicioUsuarios.guardarOActualizar(usuarios);
    }
    @DeleteMapping("/{id_usuario}")
    public void delete(@PathVariable("id_usuario") Long id_usuario){
        servicioUsuarios.borrar(id_usuario);
    }
}


