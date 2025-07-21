package com.turnat.TurnAT.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.dto.SucursalDTO;
import com.turnat.TurnAT.dto.TurnoDTO;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IDireccionService;
import com.turnat.TurnAT.services.interfaces.IEmailService;
import com.turnat.TurnAT.services.interfaces.IEstadoService;
import com.turnat.TurnAT.services.interfaces.IFechaYHoraService;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin/turnos")
@Tag(name = "Turnos", description = "Para que el admin pueda ver los turnos de la bd")
public class TurnoAdminRestController {
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
    private IDireccionService direccionService;
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IEmailService emailService;
    
    
    
    @GetMapping
    @Operation(summary = "Listar todos los turnos")
    public List<TurnoDTO> obtenerTodas() {
        return turnoService.traerTodos().stream()
            .map(turno -> new TurnoDTO(
                turno.getServicio().getIdServicio(),
                turno.getServicio().getSucursales().stream().findFirst().map(Sucursal::getIdSucursal).orElseThrow(() -> new RuntimeException("El servicio no tiene sucursal")),
                turno.getFechaYHora().getFecha().getYear(),
                turno.getFechaYHora().getFecha().getMonthValue(),
                turno.getFechaYHora().getFecha().getDayOfMonth(),
                turno.getFechaYHora().getHora().toString(),
                turno.getCliente().getIdPersona()
            ))
            .toList();
    }
    
    
    
    
    
    
}
