package negocio;

import dao.ServicioDao;
import datos.Servicio;
import java.util.List;

public class ServicioABM {
    private ServicioDao dao = new ServicioDao();

    // --- Altas, Bajas, Modificaciones ---
    public int agregar(String nombre, String descripcion) {
        // Validar que no exista un servicio con el mismo nombre
        if (existeServicio(nombre)) {
            throw new IllegalArgumentException("Ya existe un servicio con ese nombre.");
        }

        Servicio servicio = new Servicio(nombre, descripcion);
        return dao.agregar(servicio);
    }

    public void actualizar(int idServicio, String nombre, String descripcion) {
        Servicio servicio = dao.traer(idServicio);
        if (servicio == null) {
            throw new IllegalArgumentException("No existe el servicio con ID: " + idServicio);
        }

        // Validar que el nombre no esté duplicado (excepto para este servicio)
        if (existeServicioConOtroId(nombre, idServicio)) {
            throw new IllegalArgumentException("Otro servicio ya usa ese nombre.");
        }

        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        dao.actualizar(servicio);
    }

    public void eliminar(int idServicio) {
        Servicio servicio = dao.traer(idServicio);
        if (servicio == null) {
            throw new IllegalArgumentException("Servicio no encontrado con ID: " + idServicio);
        }
        
        // Validar que no tenga turnos asociados (opcional, si aplica)
        if (tieneTurnosAsociados(idServicio)) {
            throw new IllegalStateException("No se puede eliminar: el servicio tiene turnos asignados.");
        }
        
        dao.eliminar(servicio);
    }

    // --- Consultas ---
    public Servicio traer(int idServicio) {
        return dao.traer(idServicio);
    }

    public List<Servicio> traerTodos() {
        return dao.traer();
    }

    public Servicio traerPorNombre(String nombre) {
        List<Servicio> servicios = dao.traer();
        return servicios.stream()
            .filter(s -> s.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }

    // --- Métodos de validación internos ---
    private boolean existeServicio(String nombre) {
        return traerPorNombre(nombre) != null;
    }

    private boolean existeServicioConOtroId(String nombre, int idExcluir) {
        List<Servicio> servicios = dao.traer();
        return servicios.stream()
            .anyMatch(s -> s.getNombre().equalsIgnoreCase(nombre) 
                && s.getIdServicio() != idExcluir);
    }

    private boolean tieneTurnosAsociados(int idServicio) {
        // (Requiere integración con TurnoDao si es necesario)
        return false; // Implementar lógica real si aplica
    }
}