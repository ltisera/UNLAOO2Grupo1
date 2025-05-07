package test;

import negocio.EstadoABM;

public class testEstado {
    public static void main(String[] args) {
        EstadoABM abm = new EstadoABM();

        System.out.println("=== Agregando estados básicos ===");
        abm.agregar("Pendiente");
        abm.agregar("Cancelado por cliente");
        abm.agregar("Disponible");

        System.out.println("\n=== Intentando agregar duplicados ===");
        abm.agregar("Pendiente"); // Mostrará advertencia

        System.out.println("\n=== Listado completo ===");
        abm.traerTodos().forEach(e -> 
            System.out.println("ID " + e.getIdEstado() + ": " + e.getDescripcion())
        );

        System.out.println("\n=== Actualizando estado ===");
        abm.actualizar(2, "Cancelado por sistema"); // Cambiar descripción
        abm.actualizar(2, "Pendiente"); // Intentar cambiar a descripción existente
    }
}