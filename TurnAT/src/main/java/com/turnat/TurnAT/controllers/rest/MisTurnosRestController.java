package com.turnat.TurnAT.controllers.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.dto.SolicitudTurnoDTO;
import com.turnat.TurnAT.dto.TurnoDTO;
import com.turnat.TurnAT.dto.TurnoVistaDTO;
import com.turnat.TurnAT.exceptions.TurnoFueraDeFecha;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.FechaYHora;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IDireccionService;
import com.turnat.TurnAT.services.interfaces.IEmailService;
import com.turnat.TurnAT.services.interfaces.IEstadoService;
import com.turnat.TurnAT.services.interfaces.IFechaYHoraService;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Ver Mis Turnos", description = "Vista de los turnos del cliente")
@RestController
@RequestMapping("/api/turnos")
@SecurityRequirement(name = "bearerAuth") //Para que acceda el swagger, se puede poner en algun metodo especifico en vez del controller entero

public class MisTurnosRestController {
	@Autowired
    private IClienteService clienteService;
	@Autowired
    private ITurnoService turnoService;
	@Autowired
    private IEstadoService estadoService;

	@GetMapping("/mios")
    public ResponseEntity<List<TurnoVistaDTO>> obtenerTurnosDelClienteAutenticado(Authentication authentication) {
    			// Obtener el mail del usuario logueado
    			String email = authentication.getName();

    			// Buscar el cliente en la base de datos
    			Cliente cliente = clienteService.buscarPorMail(email);

        List<Turno> turnos = turnoService.traerPorIdCliente(cliente.getIdPersona());

        List<TurnoVistaDTO> dtos = turnos.stream()
                .map(turno -> new TurnoVistaDTO(
                		turno.getIdTurno(),
                        turno.getFechaYHora().getFecha().toString(),
                        turno.getFechaYHora().getHora().toString(),
                        turno.getServicio().getSucursales().stream().findFirst().map(Sucursal::getNombre).orElseThrow(() -> new RuntimeException("El servicio no tiene sucursal")),
                        turno.getServicio().getNombre(),
                        turno.getEstado().getDescripcion()))
                .toList();

        return ResponseEntity.ok(dtos);
    }
	
	@PostMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") int idTurno ){
    	try {
    		Estado estado = estadoService.traerPorDescripcion("cancelado");
    		Turno turno = turnoService.traerPorId(idTurno);
    		turno.setEstado(estado);
    		turnoService.actualizar(turno);
    		return ResponseEntity.ok("Turno cancelado");
    		
    	}catch(Exception e){
    		 return ResponseEntity.badRequest().body("No se pudo cancelar el turno, intenta mas tarde");
    	}
	}
	
	

}
