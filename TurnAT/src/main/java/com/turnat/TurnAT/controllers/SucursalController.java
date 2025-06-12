/*package com.turnat.TurnAT.controllers;

import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/sucursalAdmin")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @Autowired
    private IServicioService servicioService;

    
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("sucursal", new Sucursal());
        model.addAttribute("direccion", new Direccion());
        List<Servicio> servicios = servicioService.traerTodos();
        model.addAttribute("listaServicios", servicios);
        return "registroSucursal";
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

    // Opcional: listado de sucursales
    @GetMapping("/listado")
    public String mostrarSucursales(Model model) {
        model.addAttribute("sucursales", sucursalService.traerTodos());
        return "listadoSucursal";
    }
}*/