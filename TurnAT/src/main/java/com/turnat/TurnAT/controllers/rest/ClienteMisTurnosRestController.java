package com.turnat.TurnAT.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.dto.ListadoTurnoDTO;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/cliente/misTurnos")
public class ClienteMisTurnosRestController {
	

    @Autowired
    private ITurnoService turnoService; 
   
    @GetMapping
    @Operation(summary = "Listar todos los turnos del cliente")
    public List<ListadoTurnoDTO> obtenerTodos(@RequestParam int idCliente) {
    	
        return turnoService.traerPorIdCliente(idCliente).stream()
            .map(turno -> new ListadoTurnoDTO(
                turno.getIdTurno(),
                turno.getCliente().getNombre(),
                turno.getServicio().getNombre(),
                turno.getServicio().getSucursales().stream().findFirst().map(Sucursal::getNombre).orElseThrow(() -> new RuntimeException("El servicio no tiene sucursal")),
                turno.getEstado().getDescripcion(),
                turno.getFechaYHora().getFecha().getYear(),               
                turno.getFechaYHora().getFecha().getMonthValue(),
                turno.getFechaYHora().getFecha().getDayOfMonth(),
                turno.getFechaYHora().getHora().toString()
              
            ))
            .toList();
    }
    
	

}
