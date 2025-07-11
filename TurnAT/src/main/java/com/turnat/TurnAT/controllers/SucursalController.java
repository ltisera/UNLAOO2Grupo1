package com.turnat.TurnAT.controllers;


import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.models.entities.Empleado;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.services.interfaces.IDireccionService;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin/sucursal")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @Autowired
    private IServicioService servicioService;
    @Autowired
    private IDireccionService direccionService;

    
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("sucursal", new Sucursal());
        model.addAttribute("direccion", new Direccion());
        List<Servicio> servicios = servicioService.traerTodos();
        model.addAttribute("listaServicios", servicios);
        return "sucursalAdminRegistro";
    }

    @PostMapping("/registro")
    public String registrarSucursal(
            @ModelAttribute("sucursal") Sucursal sucursal,
            @ModelAttribute("direccion") Direccion direccion,
            @RequestParam(value = "servicioIds", required = false) List<Integer> servicioIds
    ) {
        sucursal.setDireccion(direccion);

        if (servicioIds != null && !servicioIds.isEmpty()) {
            Set<Servicio> serviciosSeleccionados = new HashSet<>();
            for (Integer id : servicioIds) {
                Servicio servicio = servicioService.traerPorId(id);
                serviciosSeleccionados.add(servicio);
            }
            sucursal.setServicios(serviciosSeleccionados);
        }

        sucursalService.agregar(sucursal);
        return "redirect:/admin/sucursal/listado";
    }

    // listado de sucursales
    @GetMapping("/listado")
    public String mostrarSucursales(Model model) {
        model.addAttribute("sucursales", sucursalService.traerTodos());
        return "sucursalAdminListado";
    }
 // Eliminar sucursal
    @GetMapping("/eliminar/{id}")
    public String eliminarSucursal(@PathVariable("id") int id) {
        sucursalService.eliminar(id);
        return "redirect:/admin/sucursal/listado";
    }
 // Mostrar formulario para editar sucursal
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
    	Sucursal sucursal = sucursalService.traerPorId(id);
        model.addAttribute("sucursal", sucursal);
        List<Direccion> direccion = direccionService.traerTodos();
        model.addAttribute("direccion", direccion);
        
        return "sucursalAdminEditar";
    }
    // Procesar formulario para actualizar empleado
    @PostMapping("/editar/{id}")
    public String actualizarSucursal(@PathVariable("id") int id,
                                     @ModelAttribute("sucursal") Sucursal sucursal) {

        Sucursal existente = sucursalService.traerPorId(id);

        existente.setNombre(sucursal.getNombre());
        existente.setTelefono(sucursal.getTelefono());

        // actualizar la dirección (persistir primero)
        Direccion nuevaDireccion = existente.getDireccion();

        if (nuevaDireccion == null) {
            nuevaDireccion = new Direccion();
        }

        nuevaDireccion.setLocalidad(sucursal.getDireccion().getLocalidad());
        nuevaDireccion.setCalle(sucursal.getDireccion().getCalle());
        nuevaDireccion.setAltura(sucursal.getDireccion().getAltura());
        nuevaDireccion.setDepto(sucursal.getDireccion().getDepto());

        direccionService.actualizar(nuevaDireccion); // persistir dirección actualizada

        existente.setDireccion(nuevaDireccion); // reasignar

        sucursalService.actualizar(existente);
        return "redirect:/admin/sucursal/listado";
    }

    
    
}