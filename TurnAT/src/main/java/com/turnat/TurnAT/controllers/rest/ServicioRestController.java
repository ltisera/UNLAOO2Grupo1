package com.turnat.TurnAT.controllers.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.dto.ServicioDTO;
import com.turnat.TurnAT.dto.DisponibleDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/servicios")
@Tag(name = "Servicios", description = "Operaciones CRUD sobre recursos Servicio")
public class ServicioRestController {

    private final IServicioService servicioService;

    public ServicioRestController(IServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los servicios")
    public List<ServicioDTO> listar() {
        return servicioService.traerTodos().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un servicio por su ID")
    public ServicioDTO obtenerPorId(@PathVariable int id) {
        return toDTO(servicioService.traerPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un nuevo servicio")
    public ServicioDTO crear(@RequestBody ServicioDTO dto) {
        Servicio entidad = fromDTO(dto);
        Servicio guardado = servicioService.agregar(entidad);
        return toDTO(guardado);
    }

    // Helper methods
    private ServicioDTO toDTO(Servicio s) {
        DisponibleDTO d = new DisponibleDTO(
            s.getDisponibilidad().getHoraInicio(),
            s.getDisponibilidad().getHoraFin(),
            s.getDisponibilidad().isLunes(),
            s.getDisponibilidad().isMartes(),
            s.getDisponibilidad().isMiercoles(),
            s.getDisponibilidad().isJueves(),
            s.getDisponibilidad().isViernes(),
            s.getDisponibilidad().isSabado(),
            s.getDisponibilidad().isDomingo()
        );
        int minutos = s.getDuracion().toSecondOfDay() / 60;
        return new ServicioDTO(
            s.getIdServicio(),
            s.getNombre(),
            s.getDescripcion(),
            minutos,
            List.of(d)
        );
    }

    private Servicio fromDTO(ServicioDTO dto) {
        Servicio s = new Servicio();
        s.setIdServicio(dto.idServicio());
        s.setNombre(dto.nombre());
        s.setDescripcion(dto.descripcion());
        s.setDuracion(java.time.LocalTime.ofSecondOfDay(dto.duracionMinutos() * 60L));

        // Mapea disponibilidad
        DisponibleDTO d = dto.disponibles().get(0);
        com.turnat.TurnAT.models.entities.Disponible disp = new com.turnat.TurnAT.models.entities.Disponible();
        disp.setHoraInicio(d.horaInicio());
        disp.setHoraFin(d.horaFin());
        disp.setLunes(d.lunes());
        disp.setMartes(d.martes());
        disp.setMiercoles(d.miercoles());
        disp.setJueves(d.jueves());
        disp.setViernes(d.viernes());
        disp.setSabado(d.sabado());
        disp.setDomingo(d.domingo());
        s.setDisponibilidad(disp);

        return s;
    }
}
