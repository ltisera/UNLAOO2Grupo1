package com.turnat.TurnAT.controllers.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.dto.TurnoDTO;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.FechaYHora;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IEstadoService;
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
    private IClienteService clienteService;
    @Autowired
    private IFechaYHoraService fechaYHoraService;
    @Autowired
    private IEstadoService estadoService;

    @Autowired
    private ITurnoService turnoService;

    @GetMapping("/servicios-todos")
    public List<Servicio> traerTodosLosServicios() {
        return servicioService.traerTodos();
    }
    
    
    // Obtener la sucursal asociada a un servicio (suponiendo que solo tiene una)
    @GetMapping("/sucursales-por-servicio/{idServicio}")
    public Set<Sucursal> obtenerSucursalesPorServicio(@PathVariable int idServicio) {
        Servicio servicio = servicioService.traerPorId(idServicio);
        return servicio.getSucursales();
    }

    // Obtener días disponibles para un servicio + sucursal + mes + año
    @GetMapping("/dias-disponibles")
    public List<Integer> obtenerDiasDisponibles(
    	    @RequestParam int idServicio,
    	    @RequestParam int idSucursal,
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
    
    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarTurno(@RequestBody TurnoDTO dto) {
        Cliente cliente = clienteService.traerPorId(dto.getIdCliente());
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente no encontrado");
        }

        Servicio servicio = servicioService.traerPorId(dto.getIdServicio());
        if (servicio == null) {
            return ResponseEntity.badRequest().body("Servicio no encontrado");
        }

        // Buscamos el estado "Pendiente" (o el que uses para nuevo turno)
        Estado estado = estadoService.traerPorDescripcion("confirmado");
        if (estado == null) {
            return ResponseEntity.badRequest().body("Estado confirmado no encontrado");
        }

        // Buscamos o creamos la FechaYHora
        LocalDate fecha = LocalDate.of(dto.getAnio(), dto.getMes(), dto.getDia());
        LocalTime hora = LocalTime.parse(dto.getHora());
        
        FechaYHora fechaYHora = fechaYHoraService.buscarPorFechaHora(fecha, hora);
        if (fechaYHora == null) {
            fechaYHora = new FechaYHora(fecha, hora);
            fechaYHoraService.agregar(fechaYHora);
        }

        Turno turno = new Turno(cliente, servicio, estado, fechaYHora);
        turnoService.agregar(turno);

        return ResponseEntity.ok("Turno confirmado");
    }
}

