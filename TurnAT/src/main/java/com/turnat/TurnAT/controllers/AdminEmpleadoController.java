package com.turnat.TurnAT.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.turnat.TurnAT.models.entities.Empleado;
import com.turnat.TurnAT.models.entities.Rol;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.repositories.IRolRepository;
import com.turnat.TurnAT.services.interfaces.IEmpleadoService;
import com.turnat.TurnAT.services.interfaces.IServicioService;

@Controller
@RequestMapping("/admin/empleado")
public class AdminEmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IServicioService servicioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectar PasswordEncoder
    
    @Autowired
    private IRolRepository rolRepository; // Inyectar RolRepository

    // Mostrar listado de empleados
    @GetMapping("/listado")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.traerTodos();
        model.addAttribute("empleados", empleados);
        return "empleadoAdminListado"; // Vista Thymeleaf - lista de empleados (crear este template)
    }

    // Mostrar formulario para agregar empleado
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("empleado", new Empleado());
        List<Servicio> servicios = servicioService.traerTodos();
        model.addAttribute("servicios", servicios); // Para selección en dropdown
        return "empleadoAdminRegistro"; // Vista Thymeleaf para formulario registro (crear)
    }

    // Procesar formulario para agregar empleado
    @PostMapping("/registro")
    public String registrarEmpleado(@ModelAttribute Empleado empleado,
                                   @RequestParam("servicioId") Optional<Integer> servicioId) {
        // Encriptar la contraseña
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
        // Asignar rol empleado
        Rol rol = rolRepository.findByNombre("EMPLEADO").orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);
        empleado.setRoles(roles);
        // Asociar servicio si fue seleccionado
        if (servicioId.isPresent()) {
            Servicio servicio = servicioService.traerPorId(servicioId.get());
            empleado.setServicio(servicio);
        } else {
            empleado.setServicio(null);
        }
        empleadoService.agregar(empleado);
        return "redirect:/admin/empleado/listado";
    }

    // Mostrar formulario para editar empleado
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Empleado empleado = empleadoService.traerPorId(id);
        model.addAttribute("empleado", empleado);
        List<Servicio> servicios = servicioService.traerTodos();
        model.addAttribute("servicios", servicios);
        return "empleadoAdminEditar"; // Vista Thymeleaf para editar (crear)
    }

    // Procesar formulario para actualizar empleado
    @PostMapping("/editar/{id}")
    public String actualizarEmpleado(@PathVariable("id") int id,
                                     @ModelAttribute Empleado empleado,
                                     @RequestParam("servicioId") Optional<Integer> servicioId) {
        Empleado existente = empleadoService.traerPorId(id);
        // Actualizar campos permitidos
        existente.setNombre(empleado.getNombre());
        existente.setApellido(empleado.getApellido());
        existente.setDni(empleado.getDni());
        existente.setEmail(empleado.getEmail());
        existente.setLegajo(empleado.getLegajo());
        if (servicioId.isPresent()) {
            Servicio servicio = servicioService.traerPorId(servicioId.get());
            existente.setServicio(servicio);
        } else {
            existente.setServicio(null);
        }

        empleadoService.actualizar(existente);
        return "redirect:/admin/empleado/listado";
    }

    // Eliminar empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") int id) {
        empleadoService.eliminar(id);
        return "redirect:/admin/empleado/listado";
    }
}

