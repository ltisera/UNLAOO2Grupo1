package com.turnat.TurnAT.services.implementations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turnat.TurnAT.dto.SolicitudTurnoDTO;
import com.turnat.TurnAT.exceptions.ServicioHoraInicioYFin;
import com.turnat.TurnAT.exceptions.TurnoFueraDeFecha;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.models.entities.Empleado;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.FechaYHora;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IEstadoRepository;
import com.turnat.TurnAT.repositories.IFechaYHoraRepository;
import com.turnat.TurnAT.repositories.IServicioRepository;
import com.turnat.TurnAT.repositories.ITurnoRepository;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

import jakarta.transaction.Transactional;

@Service
public class TurnoServiceImp implements ITurnoService {

    private final ITurnoRepository turnoRepo;
    private final IServicioRepository servicioRepo;
    private final IFechaYHoraRepository fechaYHoraRepo;
    private final IClienteRepository clienteRepo; // para buscar cliente en confirmarTurno
    private final IEstadoRepository estadoRepo;
    public TurnoServiceImp(
        ITurnoRepository turnoRepo,
        IServicioRepository servicioRepo,
        IFechaYHoraRepository fechaYHoraRepo,
        IClienteRepository clienteRepo,
        IEstadoRepository estadoRepo) {
        this.turnoRepo = turnoRepo;
        this.servicioRepo = servicioRepo;
        this.fechaYHoraRepo = fechaYHoraRepo;
        this.clienteRepo = clienteRepo;
        this.estadoRepo = estadoRepo;
    }

    @Override
    public Turno agregar(Turno turno) {
        return turnoRepo.save(turno);
    }

    @Override
    public List<Turno> traerTodos() {
        return turnoRepo.findAll();
    }

    @Override
    public void eliminar(int idTurno) {
        turnoRepo.deleteById(idTurno);
    }
    @Transactional // Esto lo actualiza si el ID existe
	public Turno actualizar(Turno turno) {
		return turnoRepo.save(turno);
	}
    
    public void eliminarTodos(List<Turno> turnos) {
    	turnoRepo.deleteAll(turnos);
    }

    @Override
    public Turno traerPorId(int idTurno) {
        return turnoRepo.findById(idTurno).orElseThrow(() -> new RuntimeException("Turno no encontrado"));
    }

    public List<Turno> traerPorIdCliente(int idCliente) {
        return turnoRepo.findByIdCliente(idCliente);
    }
    

    @Override
    public List<Integer> obtenerDiasDisponibles(int idServicio, int idSucursal, int anio, int mes) {
        Servicio servicio = servicioRepo.findById(idServicio)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        Disponible disp = servicio.getDisponibilidad();

        List<Integer> diasDisponibles = new ArrayList<>();

        LocalDate fechaInicio = LocalDate.of(anio, mes, 1);
        LocalDate fechaFin = fechaInicio.withDayOfMonth(fechaInicio.lengthOfMonth());

        for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
            if (esDiaHabil(disp, fecha.getDayOfWeek().getValue())) {
                List<LocalTime> horarios = generarHorariosDisponibles(servicio, disp, fecha);
                if (!horarios.isEmpty()) {
                    diasDisponibles.add(fecha.getDayOfMonth());
                }
            }
        }

        return diasDisponibles;
    }

    @Override
    public List<String> obtenerHorariosDisponibles(int idServicio, int idSucursal, int anio, int mes, int dia) {
        Servicio servicio = servicioRepo.findById(idServicio)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        Disponible disp = servicio.getDisponibilidad();
        LocalDate fecha = LocalDate.of(anio, mes, dia);

        List<LocalTime> disponibles = generarHorariosDisponibles(servicio, disp, fecha);

        return disponibles.stream()
                .map(LocalTime::toString)
                .collect(Collectors.toList());
    }

    private List<LocalTime> generarHorariosDisponibles(Servicio servicio, Disponible disp, LocalDate fecha) {
        List<Turno> turnosDelDia = turnoRepo.findByFecha(fecha); 
        Set<LocalTime> horariosOcupados = turnosDelDia.stream()
                .map(t -> t.getFechaYHora().getHora())
                .collect(Collectors.toSet());

        List<LocalTime> horariosDisponibles = new ArrayList<>();
        LocalTime inicio = disp.getHoraInicio();
        LocalTime fin = disp.getHoraFin();
        int minutos = servicio.getDuracion().getHour() * 60 + servicio.getDuracion().getMinute();

        for (LocalTime hora = inicio; hora.plusMinutes(minutos).isBefore(fin.plusSeconds(1)); hora = hora.plusMinutes(minutos)) {
            if (!horariosOcupados.contains(hora)) {
                horariosDisponibles.add(hora);
            }
        }

        return horariosDisponibles;
    }

    private boolean esDiaHabil(Disponible d, int diaSemanaJava) {
        switch (diaSemanaJava) {
            case 1: return d.isLunes();
            case 2: return d.isMartes();
            case 3: return d.isMiercoles();
            case 4: return d.isJueves();
            case 5: return d.isViernes();
            case 6: return d.isSabado();
            case 7: return d.isDomingo();
            default: return false;
        }
    }

    @Override
    public void confirmarTurno(SolicitudTurnoDTO dto) {
    	LocalDate fecha = LocalDate.of(dto.anio(), dto.mes(), dto.dia());
        LocalTime hora = LocalTime.parse(dto.hora());

        // Validar si la fecha es válida (no en el pasado)
        if (fecha.isBefore(LocalDate.now())) {
        	throw new TurnoFueraDeFecha("No se puede asignar un turno en una fecha pasada");
        }

        // Validar si el horario ya está ocupado
        List<Turno> turnosDelDia = turnoRepo.findByFecha(fecha);
        boolean ocupado = turnosDelDia.stream()
            .anyMatch(t -> t.getFechaYHora().getHora().equals(hora));

        if (ocupado) {
            throw new RuntimeException("Disculpe, horario ya ocupado");
        }

        // Buscar servicio
        Servicio servicio = servicioRepo.findById(dto.idServicio())
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        // Buscar cliente
        Cliente cliente = clienteRepo.findById(dto.idCliente())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Buscar o crear FechaYHora
        FechaYHora fechaYHora = fechaYHoraRepo.findByFechaAndHora(fecha, hora)
            .orElseGet(() -> {
                FechaYHora nueva = new FechaYHora(fecha, hora);
                return fechaYHoraRepo.save(nueva);
            });

        // Buscar estado "confirmado"
        Estado estadoConfirmado = estadoRepo.findByDescripcion("confirmado")
            .orElseThrow(() -> new RuntimeException("Estado 'confirmado' no encontrado"));

        // Crear y guardar el turno
        Turno nuevo = new Turno();
        nuevo.setServicio(servicio);
        nuevo.setCliente(cliente);
        nuevo.setFechaYHora(fechaYHora);
        nuevo.setEstado(estadoConfirmado);

        turnoRepo.save(nuevo);
    }

}