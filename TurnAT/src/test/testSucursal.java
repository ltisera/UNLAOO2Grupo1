package test;

import negocio.SucursalABM;

public class testSucursal {
    public static void main(String[] args) {
        SucursalABM abm = new SucursalABM();

        // Agregar una sucursal
        int id = abm.agregar("Sucursal Centro", "Av. Principal 123", 123456789);
        System.out.println("Sucursal creada con ID: " + id);

        // Listar todas
        System.out.println("Todas las sucursales:");
        abm.traerTodas().forEach(System.out::println);
    }
}