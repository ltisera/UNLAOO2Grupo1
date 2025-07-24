package com.turnat.TurnAT.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.dto.ListadoTurnoDTO;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.IEstadoService;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin/turnos")
@Tag(name = "Turnos", description = "Para que el admin pueda ver los turnos de la bd")
public class TurnoAdminRestController {

    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IEstadoService estadoService;

    @GetMapping
    @Operation(summary = "Listar todos los turnos")
    public List<ListadoTurnoDTO> obtenerTodos() {
        return turnoService.traerTodos().stream()
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
    
    
    @PostMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int idTurno){
    	try {
    		Estado estado = estadoService.traerPorDescripcion("cancelado");
    		Turno turno = turnoService.traerPorId(idTurno);
    		turno.setEstado(estado);
    		turnoService.actualizar(turno);
    		return ResponseEntity.ok("Turno cancelado");
    		
    	}catch(Exception e){
    		 return ResponseEntity.badRequest().body("No se pudo eliminar el turno, intenta mas tarde");
    	}
    	
    	
    }
    
    
    
    
    
}
