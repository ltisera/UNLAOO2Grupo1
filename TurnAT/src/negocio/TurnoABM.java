package negocio;

import dao.*;
import datos.*;
import excepciones.TurnosException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TurnoABM {
    private final TurnoDao turnoDao = new TurnoDao();
    private final PersonaDao personaDao = new PersonaDao();
    private final ServicioDao servicioDao = new ServicioDao();
    private final SucursalDao sucursalDao = new SucursalDao();
    private final EstadoDao estadoDao = new EstadoDao();

    // -------------------- Métodos principales --------------------

    public int agregarTurno(LocalDateTime fechaHora, int idCliente, int idEmpleado, 
                          int idServicio, int idSucursal, int idEstado) {
        try {
            // Validar que todos los IDs existen
            if (!validarIdsExisten(idCliente, idEmpleado, idServicio, idSucursal, idEstado)) {
            	throw new TurnosException("❌ Datos erroneos");
            }

            // Validar que no exista un turno solapado
            if (existeTurnoSolapado(fechaHora, idEmpleado)) {
            	throw new TurnosException("⏰ El empleado ya tiene un turno en ese horario");
               
            }

            // Obtener todas las entidades relacionadas
            Cliente cliente = personaDao.traerCliente(idCliente);
            Empleado empleado = personaDao.traerEmpleado(idEmpleado);
            Servicio servicio = servicioDao.traer(idServicio);
            Sucursal sucursal = sucursalDao.traer(idSucursal);
            Estado estado = estadoDao.traer(idEstado);

            // Crear y guardar el turno
            Turno turno = new Turno(fechaHora, servicio, sucursal, cliente, empleado, estado);
            int idGenerado = turnoDao.agregar(turno);
            System.out.println("✅ Turno creado exitosamente (ID: " + idGenerado + ")");
            return idGenerado;

        } catch (TurnosException e) {
        	throw new TurnosException("❌ Error al crear turno: " + e.getMessage());
           
        }
    }

    public boolean cancelarTurno(int idTurno) {
        Turno turno = turnoDao.traer(idTurno);
        if (turno == null) {
        	throw new TurnosException("⚠️ No existe el turno con ID: " + idTurno);
           
        }

        // Buscar estado "Cancelado"
        Estado cancelado = encontrarEstadoCancelado();
        if (cancelado == null) {
            System.out.println("⚠️ No se encontró el estado 'Cancelado'");
            return false;
        }

        turno.setEst(cancelado);
        turnoDao.actualizar(turno);
        System.out.println("🗑️ Turno ID " + idTurno + " cancelado correctamente");
        return true;
    }

    // -------------------- Consultas --------------------

    public Turno traerTurnoCompleto(int idTurno) {
        Turno turno = turnoDao.traer(idTurno);
        if (turno == null) {
        	throw new TurnosException("⚠️ No se encontró el turno con ID: " + idTurno);
        }
        return turno;
    }

    public List<Turno> traerTodosLosTurnos() {
        List<Turno> turnos = turnoDao.traer();
        System.out.println("📋 Total de turnos: " + turnos.size());
        return turnos;
    }

    public List<Turno> traerTurnosPorCliente(int idCliente) {
        if (personaDao.traerCliente(idCliente) == null) {
        	throw new TurnosException("⚠️ No existe cliente con ID: " + idCliente);
           
        }
        
        List<Turno> turnos = turnoDao.traerTurnosCliente(idCliente);
        System.out.println("📋 Turnos encontrados para cliente ID " + idCliente + ": " + turnos.size());
        return turnos;
    }

    public List<Turno> traerTurnosDisponibles(int idServicio, int idSucursal) {
        // ASUMIMOS QUE EL ESTADO 1 ES "Disponible"
        List<Turno> turnos = turnoDao.traerPorServicioSucursalEstado(idServicio, 1, idSucursal);
        System.out.println("🎯 Turnos disponibles encontrados: " + turnos.size());
        return turnos;
    }

    // -------------------- Métodos de validación --------------------

    private boolean validarIdsExisten(int idCliente, int idEmpleado, 
                                    int idServicio, int idSucursal, int idEstado) {
        boolean valido = true;
        
        if (personaDao.traerCliente(idCliente) == null) {
        	
        	throw new TurnosException("⚠️ No existe cliente con ID: " + idCliente);
        }
        
        if (personaDao.traerEmpleado(idEmpleado) == null) {
        	throw new TurnosException("⚠️ No existe empleado con ID: " + idEmpleado);
           
        }
        
        if (servicioDao.traer(idServicio) == null) {
        	throw new TurnosException("⚠️ No existe servicio con ID: " + idServicio);
            
        }
        
        if (sucursalDao.traer(idSucursal) == null) {
        	throw new TurnosException("⚠️ No existe sucursal con ID: " + idSucursal);
            
        }
        
        if (estadoDao.traer(idEstado) == null) {
        	throw new TurnosException("⚠️ No existe estado con ID: " + idEstado);
            
        }
        
        return valido;
    }

    private boolean existeTurnoSolapado(LocalDateTime fechaHora, int idEmpleado) {
        List<Turno> turnosEmpleado = traerTurnosPorEmpleado(idEmpleado);
        return turnosEmpleado.stream()
            .anyMatch(t -> t.getFechaYHora().equals(fechaHora) && 
                         !t.getEst().getDescripcion().equalsIgnoreCase("Cancelado"));
    }

    private Estado encontrarEstadoCancelado() {
        return estadoDao.traer().stream()
            .filter(e -> e.getDescripcion().equalsIgnoreCase("Cancelado"))
            .findFirst()
            .orElse(null);
    }

    // -------------------- Métodos auxiliares --------------------

    public List<Turno> traerTurnosPorEmpleado(int idEmpleado) {
        return turnoDao.traerTurnosPorEmpleado(idEmpleado);
    }

    public void mostrarDetallesTurno(int idTurno) {
        Turno turno = turnoDao.traer(idTurno);
        if (turno == null) {
        	throw new TurnosException("⚠️ Turno no encontrado");
            
        }

        System.out.println("\n📅 Detalles del Turno ID: " + idTurno);
        System.out.println("⏰ Fecha/Hora: " + turno.getFechaYHora());
        System.out.println("👤 Cliente: " + turno.getCli().getNombre() + " " + turno.getCli().getApellido());
        System.out.println("💼 Empleado: " + turno.getEmp().getNombre() + " " + turno.getEmp().getApellido());
        System.out.println("🏢 Sucursal: " + turno.getSuc().getNombre());
        System.out.println("🛎️ Servicio: " + turno.getSrv().getNombre());
        System.out.println("📌 Estado: " + turno.getEst().getDescripcion());
    }

    public boolean reagendarTurno(int idTurno, LocalDateTime nuevaFecha) {
        Turno turno = turnoDao.traer(idTurno);
        if (turno == null) {
        	throw new TurnosException("⚠️ Turno no encontrado");
           
        }

        if (existeTurnoSolapado(nuevaFecha, turno.getEmp().getIdPersona())) {
        	throw new TurnosException("⏰ El empleado ya tiene un turno en el nuevo horario");
            
        }

        turno.setFechaYHora(nuevaFecha);
        turnoDao.actualizar(turno);
        System.out.println("🔄 Turno reagendado exitosamente");
        return true;
    }
    
    public List<Turno> traerTurnosEntreFechas(LocalDateTime desde, LocalDateTime hasta){
    	if(desde.isAfter(hasta)) {
    		throw new TurnosException("⚠️ La fecha desde es mayor a la fecha Hasta");
    	}
    	
        return turnoDao.traerPorRangoFechas(desde, hasta);
    }

    public List<Turno> traerTurnosHoy() {
        return turnoDao.traerPorDia(LocalDate.now());
    }

    public List<Turno> traerTurnosEsteMes() {
        LocalDate hoy = LocalDate.now();
        return turnoDao.traerPorMes(hoy.getYear(), hoy.getMonthValue());
    }

    public List<Turno> traerTurnosCanceladosUltimaSemana() {
        LocalDateTime haceUnaSemana = LocalDateTime.now().minusDays(7);
        return turnoDao.traerPorRangoFechasYEstado(haceUnaSemana, LocalDateTime.now(), 2);// ID de estado "Cancelado" 
    }
    
    //1.6. Mínimo cuatro consultas por una fecha y un atributo de clase
    public List<Turno> traerPorRangoFechasYEstado(LocalDateTime desde, LocalDateTime hasta, int idEstado){
    	if(turnoDao.traerPorRangoFechasYEstado(desde, hasta, idEstado)== null) {
    		throw new TurnosException("No hay turnos en ese intervalo de fechas con ese estado");
    	}
    	return turnoDao.traerPorRangoFechasYEstado(desde, hasta, idEstado);
    }
    public List<Turno> traerPorRangoFechasYEmpleado(LocalDateTime desde, LocalDateTime hasta, int idEmpleado){
    	if(turnoDao.traerPorRangoFechasYEmpleado(desde, hasta, idEmpleado)== null) {
    		throw new TurnosException("No hay turnos en ese intervalo de fechas de ese empleado");
    	}
    	return turnoDao.traerPorRangoFechasYEmpleado(desde, hasta, idEmpleado);
    }
    
    public List<Turno> traerPorRangoFechasYServicio(LocalDateTime desde, LocalDateTime hasta, int idServicio){
    	if(turnoDao.traerPorRangoFechasYServicio(desde, hasta, idServicio)== null) {
    		throw new TurnosException("No hay turnos en ese intervalo de fechas de ese servicio");
    	}
    	return turnoDao.traerPorRangoFechasYServicio(desde, hasta, idServicio);
    }
    public List<Turno> traerPorRangoFechasYCliente(LocalDateTime desde, LocalDateTime hasta, int idCliente){
    	if(turnoDao.traerPorRangoFechasYCliente(desde, hasta, idCliente)== null) {
    		throw new TurnosException("No hay turnos en ese intervalo de fechas de ese cliente");
    	}
    	return turnoDao.traerPorRangoFechasYCliente(desde, hasta, idCliente);
    }
    
    
}