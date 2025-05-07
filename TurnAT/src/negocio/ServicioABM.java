package negocio;

import dao.ServicioDao;
import datos.Servicio;
import excepciones.TurnosException;
import java.util.List;

public class ServicioABM {
    private ServicioDao dao = new ServicioDao();

    public int agregar(String nombre, String descripcion) {
        if (existeServicio(nombre)) {
            throw new TurnosException("⚠️ Ya existe un servicio con el nombre: \"" + nombre + "\"");
             // Retorno especial para indicar que no se agregó
        }
        
        Servicio servicio = new Servicio(nombre, descripcion);
        int idGenerado = dao.agregar(servicio);
        System.out.println("✅ Servicio \"" + nombre + "\" agregado correctamente (ID: " + idGenerado + ")");
        return idGenerado;
    }

    // Resto de métodos (actualizar, eliminar, traer, etc.) se mantienen igual
    private boolean existeServicio(String nombre) {
        Servicio servicioExistente = traerPorNombre(nombre);
        return servicioExistente != null;
    }
    public Servicio traer(int idServicio) {
    	if(dao.traer(idServicio)==null) {
    		throw new TurnosException("⚠️ No existe servicio con ese ID");
    	}
    	return dao.traer(idServicio);
    }


    public Servicio traerPorNombre(String nombre) {
        List<Servicio> servicios = dao.traer();
        return servicios.stream()
            .filter(s -> s.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }
}