package test;

import excepciones.TurnosException;
import negocio.SucursalABM;

public class testSucursal {
    public static void main(String[] args) {
        SucursalABM abm = new SucursalABM();
        
        // Intenta agregar la misma sucursal dos veces
        try {
        	abm.agregar("Sucursal Centro", "Av. Principal 123", 123456789);
        	abm.agregar("Sucursal Centro", "Av. Principal 123", 111111111); // Este fallará
        }catch (TurnosException e) {
        	System.out.println(e.getMessage());
        }
        // Listar
        abm.traerTodos().forEach(System.out::println);
    }
}