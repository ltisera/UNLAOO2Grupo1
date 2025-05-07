package negocio;

import dao.EstadoDao;
import datos.Estado;
import java.util.List;

public class EstadoABM {
    private EstadoDao dao = new EstadoDao();

    // --- Altas, Bajas, Modificaciones ---
    public int agregar(String descripcion) {
        // Validar si ya existe
        Estado existente = dao.traerPorDescripcion(descripcion);
        if (existente != null) {
            System.out.println("⚠️ Ya existe el estado '" + descripcion + "' con ID: " + existente.getIdEstado());
            return existente.getIdEstado(); // Devolvemos el ID existente
        }

        Estado estado = new Estado(descripcion);
        int id = dao.agregar(estado);
        System.out.println("✅ Estado '" + descripcion + "' creado con ID: " + id);
        return id;
    }

    public void actualizar(int idEstado, String nuevaDescripcion) {
        Estado estado = dao.traer(idEstado);
        if (estado == null) {
            System.out.println("⚠️ No existe el estado con ID: " + idEstado);
            return;
        }

        // Validar si la nueva descripción ya existe en otro estado
        Estado estadoConMismaDesc = dao.traerPorDescripcion(nuevaDescripcion);
        if (estadoConMismaDesc != null && estadoConMismaDesc.getIdEstado() != idEstado) {
            System.out.println("⚠️ Ya existe otro estado con la descripción: '" + nuevaDescripcion + "'");
            return;
        }

        estado.setDescripcion(nuevaDescripcion);
        dao.actualizar(estado);
        System.out.println("✏️ Estado actualizado correctamente (ID: " + idEstado + ")");
    }

    public void eliminar(int idEstado) {
        Estado estado = dao.traer(idEstado);
        if (estado == null) {
            System.out.println("⚠️ No se encontró estado con ID: " + idEstado);
            return;
        }

        // Validar uso (opcional)
        if (estaEnUso(idEstado)) {
            System.out.println("⚠️ No se puede eliminar - El estado está en uso");
            return;
        }

        dao.eliminar(estado);
        System.out.println("🗑️ Estado eliminado correctamente (ID: " + idEstado + ")");
    }

    // --- Consultas ---
    public Estado traer(int idEstado) {
        return dao.traer(idEstado);
    }

    public List<Estado> traerTodos() {
        return dao.traer();
    }

    // --- Métodos de validación internos ---
    private boolean estaEnUso(int idEstado) {
        // (Opcional: Implementar lógica real si es necesario)
        return false;
    }
}