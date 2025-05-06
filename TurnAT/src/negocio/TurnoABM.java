package negocio;

import dao.*;
import datos.*;
import java.time.LocalDateTime;
import java.util.List;

public class TurnoABM {
    private TurnoDao turnoDao = new TurnoDao();
    private ClienteDao clienteDao = new ClienteDao();
    private EmpleadoDao empleadoDao = new EmpleadoDao();
    private ServicioDao servicioDao = new ServicioDao();
    private SucursalDao sucursalDao = new SucursalDao();
    private EstadoDao estadoDao = new EstadoDao();

    // --- Métodos principales ---
    public int agregar(LocalDateTime fechaYHora, int idCliente, int idEmpleado, 
                      int idServicio, int idSucursal, int idEstado) {
        // Validar solapamiento
        if (turnoDao.existeSolapamiento(fechaYHora, idEmpleado)) {
            throw new IllegalArgumentException("El empleado ya tiene un turno en esa fecha/hora.");
        }

        // Obtener objetos relacionados (validar que existan)
        Cliente cliente = clienteDao.traer(idCliente);
        Empleado empleado = empleadoDao.traer(idEmpleado);
        Servicio servicio = servicioDao.traer(idServicio);
        Sucursal sucursal = sucursalDao.traer(idSucursal);
        Estado estado = estadoDao.traer(idEstado);

        if (cliente == null || empleado == null || servicio == null || sucursal == null || estado == null) {
            throw new IllegalArgumentException("Datos inválidos (IDs no existen).");
        }

        // Crear y guardar el turno
        Turno turno = new Turno(fechaYHora, servicio, sucursal, cliente, empleado, estado);
        return turnoDao.agregar(turno);
    }

    public void cancelarTurno(int idTurno) {
        Turno turno = turnoDao.traer(idTurno);
        if (turno == null) {
            throw new IllegalArgumentException("Turno no encontrado.");
        }
        Estado cancelado = estadoDao.traer(2); // Asumiendo que ID 2 = "Cancelado"
        turno.setEst(cancelado);
        turnoDao.actualizar(turno);
    }

    public List<Turno> traerTurnosPorEmpleado(int idEmpleado) {
        return turnoDao.traerPorEmpleado(idEmpleado);
    }

    public List<Turno> traerTodosLosTurnos() {
        return turnoDao.traerTodos();
    }

    // --- Métodos adicionales (opcionales) ---
    public List<Turno> traerTurnosPorFecha(LocalDateTime fecha) {
        // Implementar filtro por fecha (puedes agregarlo en TurnoDao)
        // Ejemplo: Query con BETWEEN para un rango de horas.
        return null;
    }
}