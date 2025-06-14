package com.turnat.TurnAT.controllers.rest;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.services.interfaces.IFechaYHoraService;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@RestController
@RequestMapping("/api/turnos")
public class TurnoRestController {

    @Autowired
    private IServicioService servicioService;

    @Autowired
    private ISucursalService sucursalService;

    @Autowired
    private IFechaYHoraService fechaYHoraService;

    @Autowired
    private ITurnoService turnoService;

    // Obtener la sucursal asociada a un servicio (suponiendo que solo tiene una)
    @GetMapping("/sucursal-por-servicio/{idServicio}")
    public Sucursal obtenerSucursal(@PathVariable int idServicio) {
        Servicio servicio = servicioService.traerPorId(idServicio);
        return servicio.getSucursales().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada para el servicio"));
    }

    // Obtener días disponibles para un servicio + sucursal + mes + año
    @GetMapping("/dias-disponibles")
    public List<Integer> obtenerDiasDisponibles(
            @RequestParam int idServicio,
            @RequestParam int anio,
            @RequestParam int mes) {
        
        Servicio servicio = servicioService.traerPorId(idServicio);
        Sucursal sucursal = servicio.getSucursales().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada para el servicio"));

        return turnoService.obtenerDiasDisponibles(idServicio, sucursal.getIdSucursal(), anio, mes);
    }

    // Obtener horarios disponibles para un día
    @GetMapping("/horarios-disponibles")
    public List<String> obtenerHorariosDisponibles(
            @RequestParam int idServicio,
            @RequestParam int anio,
            @RequestParam int mes,
            @RequestParam int dia) {
        
        Servicio servicio = servicioService.traerPorId(idServicio);
        Sucursal sucursal = servicio.getSucursales().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada para el servicio"));

        return turnoService.obtenerHorariosDisponibles(idServicio, sucursal.getIdSucursal(), anio, mes, dia);
    }
}

