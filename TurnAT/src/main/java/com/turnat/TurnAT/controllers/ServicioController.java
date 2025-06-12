package com.turnat.TurnAT.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.IDisponibleService;

@Controller
@RequestMapping("/admin/servicio")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @Autowired
    private IDisponibleService disponibleService; // Inyectar el servicio de Disponible

    // Listar servicios
    @GetMapping("/listado")
    public String listarServicios(Model model) {
        List<Servicio> servicios = servicioService.traerTodos();
        model.addAttribute("servicios", servicios);
        return "servicioAdminListado";
    }

    // Mostrar formulario para registrar servicio
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("servicio", new Servicio());
        List<Disponible> disponibles = disponibleService.traerTodos(); // Obtener disponibles
        model.addAttribute("disponibles", disponibles); // Agregar disponibles al modelo
        return "servicioAdminRegistro";
    }

    // Procesar registro de servicio
    @PostMapping("/registro")
    public String registrarServicio(@ModelAttribute Servicio servicio, @RequestParam("disponibleId") Optional<Integer> disponibleId) {
        if (disponibleId.isPresent()) {
            Disponible disponible = disponibleService.traerPorId(disponibleId.get());
            servicio.setDisponibilidad(disponible); // Asignar el disponible al servicio
        }
        servicioService.agregar(servicio);
        return "redirect:/admin/servicio/listado";
    }

    // Mostrar formulario para editar servicio
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Servicio servicio = servicioService.traerPorId(id);
        model.addAttribute("servicio", servicio);
        List<Disponible> disponibles = disponibleService.traerTodos(); // Obtener disponibles
        model.addAttribute("disponibles", disponibles); // Agregar disponibles al modelo
        return "servicioAdminEditar";
    }

    // Procesar actualización de servicio
    @PostMapping("/editar/{id}")
    public String actualizarServicio(@PathVariable("id") int id, @ModelAttribute Servicio servicio) {
        Servicio existente = servicioService.traerPorId(id);
        existente.setNombre(servicio.getNombre());
        existente.setDescripcion(servicio.getDescripcion());
        existente.setDuracion(servicio.getDuracion());
        existente.setDisponibilidad(servicio.getDisponibilidad()); // Ajusta si manejas Disponibles

        servicioService.agregar(existente);
        return "redirect:/admin/servicio/listado";
    }

    // Eliminar servicio
    @GetMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable("id") int id) {
        servicioService.eliminar(id);
        return "redirect:/admin/servicio/listado";
    }
}
