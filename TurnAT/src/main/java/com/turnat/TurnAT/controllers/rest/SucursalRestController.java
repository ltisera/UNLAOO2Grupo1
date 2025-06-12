package com.turnat.TurnAT.controllers.rest;

import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.services.interfaces.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sucursales")
@CrossOrigin(origins = "*") // Permite acceder desde cualquier frontend
public class SucursalRestController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public List<Sucursal> obtenerTodas() {
    	System.out.println("Estoy resteando");
        return sucursalService.traerTodos();
    }
}