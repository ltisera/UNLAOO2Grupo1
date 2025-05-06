package test;

import negocio.SucursalABM;

public class testSucursal {
    public static void main(String[] args) {
        SucursalABM abm = new SucursalABM();
        
        // Intenta agregar la misma sucursal dos veces
        abm.agregar("Sucursal Centro", "Av. Principal 123", 123456789);
        abm.agregar("Sucursal Centro", "Av. Principal 123", 111111111); // Este fallará
        
        // Listar
        abm.traerTodos().forEach(System.out::println);
    }
}