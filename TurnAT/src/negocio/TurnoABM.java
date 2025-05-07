package negocio;

import dao.*;
import datos.*;
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
                return -1;
            }

            // Validar que no exista un turno solapado
            if (existeTurnoSolapado(fechaHora, idEmpleado)) {
                System.out.println("⏰ El empleado ya tiene un turno en ese horario");
                return -1;
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

        } catch (Exception e) {
            System.out.println("❌ Error al crear turno: " + e.getMessage());
            return -1;
        }
    }

    public boolean cancelarTurno(int idTurno) {
        Turno turno = turnoDao.traer(idTurno);
        if (turno == null) {
            System.out.println("⚠️ No existe el turno con ID: " + idTurno);
            return false;
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
            System.out.println("⚠️ No se encontró el turno con ID: " + idTurno);
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
            System.out.println("⚠️ No existe cliente con ID: " + idCliente);
            return List.of(); // Retorna lista vacía
        }
        
        List<Turno> turnos = turnoDao.traerTurnosCliente(idCliente);
        System.out.println("📋 Turnos encontrados para cliente ID " + idCliente + ": " + turnos.size());
        return turnos;
    }

    public List<Turno> traerTurnosDisponibles(int idServicio, int idSucursal) {
        // Asumimos que el estado 1 es "Disponible"
        List<Turno> turnos = turnoDao.traerPorServicioSucursalEstado(idServicio, 1, idSucursal);
        System.out.println("🎯 Turnos disponibles encontrados: " + turnos.size());
        return turnos;
    }

    // -------------------- Métodos de validación --------------------

    private boolean validarIdsExisten(int idCliente, int idEmpleado, 
                                    int idServicio, int idSucursal, int idEstado) {
        boolean valido = true;
        
        if (personaDao.traerCliente(idCliente) == null) {
            System.out.println("⚠️ No existe cliente con ID: " + idCliente);
            valido = false;
        }
        
        if (personaDao.traerEmpleado(idEmpleado) == null) {
            System.out.println("⚠️ No existe empleado con ID: " + idEmpleado);
            valido = false;
        }
        
        if (servicioDao.traer(idServicio) == null) {
            System.out.println("⚠️ No existe servicio con ID: " + idServicio);
            valido = false;
        }
        
        if (sucursalDao.traer(idSucursal) == null) {
            System.out.println("⚠️ No existe sucursal con ID: " + idSucursal);
            valido = false;
        }
        
        if (estadoDao.traer(idEstado) == null) {
            System.out.println("⚠️ No existe estado con ID: " + idEstado);
            valido = false;
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
            System.out.println("⚠️ Turno no encontrado");
            return;
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
            System.out.println("⚠️ Turno no encontrado");
            return false;
        }

        if (existeTurnoSolapado(nuevaFecha, turno.getEmp().getIdPersona())) {
            System.out.println("⏰ El empleado ya tiene un turno en el nuevo horario");
            return false;
        }

        turno.setFechaYHora(nuevaFecha);
        turnoDao.actualizar(turno);
        System.out.println("🔄 Turno reagendado exitosamente");
        return true;
    }
}