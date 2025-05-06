package negocio;

import dao.SucursalDao;
import datos.Sucursal;
import java.util.List;

public class SucursalABM {
    private SucursalDao dao = new SucursalDao();

    // --- Altas, Bajas, Modificaciones ---
    public int agregar(String nombre, String direccion, int telefono) {
        // Validar que no exista una sucursal con el mismo nombre/dirección
        if (existeSucursal(nombre, direccion)) {
            throw new IllegalArgumentException("Ya existe una sucursal con ese nombre y dirección.");
        }

        Sucursal sucursal = new Sucursal(nombre, direccion, telefono);
        return dao.agregar(sucursal);
    }

    public void actualizar(int idSucursal, String nombre, String direccion, int telefono) {
        Sucursal sucursal = dao.traer(idSucursal);
        if (sucursal == null) {
            throw new IllegalArgumentException("No existe la sucursal con ID: " + idSucursal);
        }

        // Validar unicidad (excepto para la sucursal actual)
        if (existeSucursalConOtroId(nombre, direccion, idSucursal)) {
            throw new IllegalArgumentException("Otra sucursal ya usa ese nombre/dirección.");
        }

        sucursal.setNombre(nombre);
        sucursal.setDireccion(direccion);
        sucursal.setTelefono(telefono);
        dao.actualizar(sucursal);
    }

    public void eliminar(int idSucursal) {
        Sucursal sucursal = dao.traer(idSucursal);
        if (sucursal == null) {
            throw new IllegalArgumentException("Sucursal no encontrada con ID: " + idSucursal);
        }
        // Validar que no tenga turnos asociados (depende de tu modelo)
        if (tieneTurnosAsociados(idSucursal)) {
            throw new IllegalStateException("No se puede eliminar: la sucursal tiene turnos asignados.");
        }
        dao.eliminar(sucursal);
    }

    // --- Consultas ---
    public Sucursal traer(int idSucursal) {
        return dao.traer(idSucursal);
    }

    public List<Sucursal> traerTodas() {
        return dao.traer();
    }

    // --- Métodos de validación internos ---
    private boolean existeSucursal(String nombre, String direccion) {
        List<Sucursal> sucursales = dao.traer();
        return sucursales.stream()
            .anyMatch(s -> s.getNombre().equalsIgnoreCase(nombre) 
                && s.getDireccion().equalsIgnoreCase(direccion));
    }

    private boolean existeSucursalConOtroId(String nombre, String direccion, int idExcluir) {
        List<Sucursal> sucursales = dao.traer();
        return sucursales.stream()
            .anyMatch(s -> s.getNombre().equalsIgnoreCase(nombre) 
                && s.getDireccion().equalsIgnoreCase(direccion)
                && s.getIdSucursal() != idExcluir);
    }

    private boolean tieneTurnosAsociados(int idSucursal) {
        // (Requiere integración con TurnoDao, si es necesario)
        return false; // Implementar lógica real
    }
}