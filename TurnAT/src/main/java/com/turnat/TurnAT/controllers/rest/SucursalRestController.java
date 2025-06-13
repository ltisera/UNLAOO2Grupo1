package com.turnat.TurnAT.controllers.rest;

import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.services.interfaces.ISucursalService;

import com.turnat.TurnAT.services.interfaces.IDireccionService;
import com.turnat.TurnAT.dto.SucursalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sucursales")
@CrossOrigin(origins = "*") // Permite acceder desde cualquier frontend
public class SucursalRestController {

    @Autowired
    private ISucursalService sucursalService;
    
    @Autowired
    private IDireccionService direccionService;

    @GetMapping
    public List<SucursalDTO> obtenerTodas() {
        return sucursalService.traerTodos().stream().map(SucursalDTO::new).toList();
    }
    
    @PostMapping
    public SucursalDTO crearSucursal(@RequestBody SucursalDTO sucursalDTO) {
        // Convertir DTO a entidad Dirección
    	System.out.println("PIOLAAAAAAAAAAAAAA");
        Direccion direccion = new Direccion();
        if (sucursalDTO.getDireccion() != null) {
            direccion.setLocalidad(sucursalDTO.getDireccion().getLocalidad());
            direccion.setCalle(sucursalDTO.getDireccion().getCalle());
            direccion.setAltura(sucursalDTO.getDireccion().getAltura());
            direccion.setDepto(sucursalDTO.getDireccion().getDepto());
            
            // Guardar dirección primero
            direccion = direccionService.agregar(direccion);
        }

        // Crear entidad Sucursal con la dirección ya persistida
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setTelefono(sucursalDTO.getTelefono());
        sucursal.setDireccion(direccion);

        // Guardar sucursal
        Sucursal sucursalGuardada = sucursalService.agregar(sucursal);

        // Retornar DTO
        return new SucursalDTO(sucursalGuardada);
    }
}