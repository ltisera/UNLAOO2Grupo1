package com.turnat.TurnAT.controllers.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import com.turnat.TurnAT.dto.SolicitudTurnoDTO;
import com.turnat.TurnAT.dto.TurnoDTO;
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

@RestController
@RequestMapping("/api/turnos")
//para solicitar turno
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
    private IDireccionService direccionService;
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IEmailService emailService;
    
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
    	try {
    		
    	
	    	// Validaciones básicas
	        Cliente cliente = clienteService.traerPorId(dto.idCliente());
	        if (cliente == null) return ResponseEntity.badRequest().body("Cliente no encontrado");
	
	        Servicio servicio = servicioService.traerPorId(dto.idServicio());
	        if (servicio == null) return ResponseEntity.badRequest().body("Servicio no encontrado");
	
	        Sucursal sucursal = sucursalService.traerPorId(dto.idSucursal());
	        if (sucursal == null) return ResponseEntity.badRequest().body("Sucursal no encontrada");
	
	        Direccion direccion = direccionService.traerPorId(sucursal.getDireccion().getIdDireccion());
	        if (direccion == null) return ResponseEntity.badRequest().body("Dirección no encontrada");
	
	        Estado estado = estadoService.traerPorDescripcion("confirmado");
	        if (estado == null) return ResponseEntity.badRequest().body("Estado confirmado no encontrado");
	
	        // Armar DTO para pasar al service
	        SolicitudTurnoDTO sdto = new SolicitudTurnoDTO(
	            dto.idServicio(), dto.anio(), dto.mes(), dto.dia(), dto.hora(), dto.idCliente()
	        );
	
	        // Confirmar turno (esto guarda el Turno)
	        turnoService.confirmarTurno(sdto);
	
	        // Enviar correo (opcional)
	        DateTimeFormatter fechaFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        DateTimeFormatter horaFmt = DateTimeFormatter.ofPattern("HH:mm");
	        String asunto = "¡Turno solicitado en TurnAT!";
	        String cuerpo = "Hola " + cliente.getNombre() + ",\n\n" +
	                        "Tu turno para el servicio *" + servicio.getNombre() + "* fue confirmado.\n" +
	                        "📅 Fecha: " + LocalDate.of(dto.anio(), dto.mes(), dto.dia()).format(fechaFmt) + "\n" +
	                        "🕒 Hora: " + LocalTime.parse(dto.hora()).format(horaFmt) + " hs\n" +
	                        "🏢 Sucursal: " + sucursal.getNombre() + "\n" +
	                        "📍 Dirección: " + direccion.toString() + "\n\n" +
	                        "¡Gracias por confiar en TurnAT!";
	        emailService.enviarCorreo(cliente.getEmail(), asunto, cuerpo);
	
	        return ResponseEntity.ok("Turno confirmado");
    	} catch (TurnoFueraDeFecha e){
    		return ResponseEntity.badRequest().body(e.getMessage());
    	}
    	
    }    
    
}

