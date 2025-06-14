package com.turnat.TurnAT.controllers;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;
import com.turnat.TurnAT.services.interfaces.IDisponibleService;

@Controller
@RequestMapping("/admin/servicio")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @Autowired
    private IDisponibleService disponibleService;
    @Autowired
    private ISucursalService sucursalService;

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
        List<Sucursal> sucursales = sucursalService.traerTodos(); 
        model.addAttribute("sucursales", sucursales);
        return "servicioAdminRegistro";
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
    
    @PostMapping("/registro")
    public String registrarServicio(@ModelAttribute Servicio servicio,
                                    @RequestParam("idSucursal") int idSucursal,
                                    @RequestParam("disponibilidad.horaInicio") LocalTime horaInicio,
                                    @RequestParam("disponibilidad.horaFin") LocalTime horaFin,
                                    @RequestParam(value = "disponibilidad.lunes", defaultValue = "false") boolean lunes,
                                    @RequestParam(value = "disponibilidad.martes", defaultValue = "false") boolean martes,
                                    @RequestParam(value = "disponibilidad.miercoles", defaultValue = "false") boolean miercoles,
                                    @RequestParam(value = "disponibilidad.jueves", defaultValue = "false") boolean jueves,
                                    @RequestParam(value = "disponibilidad.viernes", defaultValue = "false") boolean viernes,
                                    @RequestParam(value = "disponibilidad.sabado", defaultValue = "false") boolean sabado,
                                    @RequestParam(value = "disponibilidad.domingo", defaultValue = "false") boolean domingo) {

        // Crear disponibilidad
        Disponible disponible = new Disponible();
        disponible.setHoraInicio(horaInicio);
        disponible.setHoraFin(horaFin);
        disponible.setLunes(lunes);
        disponible.setMartes(martes);
        disponible.setMiercoles(miercoles);
        disponible.setJueves(jueves);
        disponible.setViernes(viernes);
        disponible.setSabado(sabado);
        disponible.setDomingo(domingo);

        servicio.setDisponibilidad(disponible);

        // Buscar la sucursal seleccionada
        Sucursal sucursal = sucursalService.traerPorId(idSucursal);

        // Asociar la sucursal al servicio (inicializar el set si es null)
        if (servicio.getSucursales() == null) {
            servicio.setSucursales(new HashSet<>());
        }
        servicio.getSucursales().add(sucursal);

        // Guardar
        servicioService.agregar(servicio);

        return "redirect:/admin/servicio/listado";
    }


}
