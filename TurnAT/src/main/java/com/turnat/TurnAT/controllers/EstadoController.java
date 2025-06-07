package com.turnat.TurnAT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.services.implementations.EstadoServiceImp;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoServiceImp estadoService;

    @GetMapping
    @PostMapping
    public void crearEstado() {
    	System.out.println("ENTRE A EL CONTROLADOR");
        //return estadoService.agregarEstado(estado.getDescripcion());
    }
    
    //map
    //Traigas turno de TODOS
    
    //map
    //turno.TraerEstado()
}