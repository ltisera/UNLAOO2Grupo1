package negocio;

import dao.SucursalDao;
import datos.Sucursal;
import excepciones.TurnosException;

import java.util.List;

public class SucursalABM {
    private SucursalDao dao = new SucursalDao();

    public int agregar(String nombre, String direccion, int telefono) {
        if (existeSucursal(nombre, direccion)) {
        	throw new TurnosException("Ya existe una sucursal con nombre: \"" + nombre + "\" y dirección: \"" + direccion + "\"");
        }

        Sucursal sucursal = new Sucursal(nombre, direccion, telefono);
        int id = dao.agregar(sucursal);
        System.out.println("✅ Sucursal \"" + nombre + "\" creada con ID: " + id);
        return id;
    }

    public List<Sucursal> traerTodos() {
        List<Sucursal> sucursales = dao.traer();
        System.out.println("📋 Total de sucursales: " + sucursales.size());
        return sucursales;
    }
    
    public Sucursal traer(int idSucursal) {
    	if (dao.traer(idSucursal) == null) {
        	throw new TurnosException("NO existe una sucursal con ID: " + idSucursal);
        }
    	return dao.traer(idSucursal);
    }

    private boolean existeSucursal(String nombre, String direccion) {
        List<Sucursal> sucursales = dao.traer();
        return sucursales.stream().anyMatch(s -> 
            s.getNombre().equalsIgnoreCase(nombre) && 
            s.getDireccion().equalsIgnoreCase(direccion)
        );
    }
}