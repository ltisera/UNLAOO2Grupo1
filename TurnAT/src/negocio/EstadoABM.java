package negocio;

import dao.EstadoDao;
import datos.Estado;
import excepciones.TurnosException;

import java.util.List;

public class EstadoABM {
    private EstadoDao dao = new EstadoDao();

    // --- Altas, Bajas, Modificaciones ---
    public int agregar(String descripcion) {
        // Validar si ya existe
        Estado existente = dao.traerPorDescripcion(descripcion);
        if (existente != null) {
           throw new TurnosException("⚠️ Ya existe el estado '" + descripcion + "' con ID: " + existente.getIdEstado());
          
        }

        Estado estado = new Estado(descripcion);
        int id = dao.agregar(estado);
        System.out.println("✅ Estado '" + descripcion + "' creado con ID: " + id);
        return id;
    }

    public void actualizar(int idEstado, String nuevaDescripcion) {
        Estado estado = dao.traer(idEstado);
        if (estado == null) {
        	throw new TurnosException("⚠️ No existe el estado con ID: " + idEstado);
            
        }

        // Validar si la nueva descripción ya existe en otro estado
        Estado estadoConMismaDesc = dao.traerPorDescripcion(nuevaDescripcion);
        if (estadoConMismaDesc != null && estadoConMismaDesc.getIdEstado() != idEstado) {
        	throw new TurnosException("⚠️ Ya existe otro estado con la descripción: '" + nuevaDescripcion + "'");
           
        }

        estado.setDescripcion(nuevaDescripcion);
        dao.actualizar(estado);
        System.out.println("✏️ Estado actualizado correctamente (ID: " + idEstado + ")");
    }

    public void eliminar(int idEstado) {
        Estado estado = dao.traer(idEstado);
        if (estado == null) {
        	throw new TurnosException("⚠️ No se encontró estado con ID: " + idEstado);
        
        }

        // Validar uso (opcional)
        if (estaEnUso(idEstado)) {
        	throw new TurnosException("⚠️ No se puede eliminar - El estado está en uso");
            
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