package com.turnat.TurnAT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.services.implementations.EstadoService;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping
    public Estado crearEstado(@RequestBody Estado estado) {
        return estadoService.agregarEstado(estado.getDescripcion());
    }
}