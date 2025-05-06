package negocio;

import dao.EstadoDao;
import datos.Estado;
import java.util.List;

public class EstadoABM {
    private EstadoDao dao = new EstadoDao();

    // --- Altas, Bajas, Modificaciones ---
    public int agregar(String descripcion) {
        // Validar que no exista un estado con la misma descripción
        if (existeEstado(descripcion)) {
            throw new IllegalArgumentException("Ya existe un estado con esa descripción.");
        }

        Estado estado = new Estado(descripcion);
        return dao.agregar(estado);
    }

    public void actualizar(int idEstado, String nuevaDescripcion) {
        Estado estado = dao.traer(idEstado);
        if (estado == null) {
            throw new IllegalArgumentException("No existe el estado con ID: " + idEstado);
        }

        // Validar que la nueva descripción no esté duplicada
        if (existeEstadoConOtroId(nuevaDescripcion, idEstado)) {
            throw new IllegalArgumentException("Otro estado ya usa esa descripción.");
        }

        estado.setDescripcion(nuevaDescripcion);
        dao.actualizar(estado);
    }

    public void eliminar(int idEstado) {
        Estado estado = dao.traer(idEstado);
        if (estado == null) {
            throw new IllegalArgumentException("Estado no encontrado con ID: " + idEstado);
        }

        // Validar que no esté siendo usado en turnos (opcional)
        if (estaEnUso(idEstado)) {
            throw new IllegalStateException("No se puede eliminar: el estado está asignado a turnos.");
        }

        dao.eliminar(estado);
    }

    // --- Consultas ---
    public Estado traer(int idEstado) {
        return dao.traer(idEstado);
    }

    public List<Estado> traerTodos() {
        return dao.traer();
    }

    public Estado traerPorDescripcion(String descripcion) {
        List<Estado> estados = dao.traer();
        return estados.stream()
            .filter(e -> e.getDescripcion().equalsIgnoreCase(descripcion))
            .findFirst()
            .orElse(null);
    }

    // --- Métodos de validación internos ---
    private boolean existeEstado(String descripcion) {
        return traerPorDescripcion(descripcion) != null;
    }

    private boolean existeEstadoConOtroId(String descripcion, int idExcluir) {
        List<Estado> estados = dao.traer();
        return estados.stream()
            .anyMatch(e -> e.getDescripcion().equalsIgnoreCase(descripcion) 
                && e.getIdEstado() != idExcluir);
    }

    private boolean estaEnUso(int idEstado) {
        // (Opcional: Integrar con TurnoDao para verificar si hay turnos con este estado)
        return false; // Implementar lógica real si es necesario
    }
}