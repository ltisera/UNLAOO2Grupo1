package test;

import negocio.EstadoABM;

public class testEstado{
    public static void main(String[] args) {
        EstadoABM abm = new EstadoABM();

        // Agregar estados iniciales
        int idPendiente = abm.agregar("Pendiente");
        int idCancelado = abm.agregar("Cancelado");
        System.out.println("Estado 'Pendiente' creado con ID: " + idPendiente);

        // Listar todos
        System.out.println("Todos los estados:");
        abm.traerTodos().forEach(e -> System.out.println(e.getIdEstado() + ": " + e.getDescripcion()));

        // Actualizar
        abm.actualizar(idCancelado, "Cancelado por cliente");
    }
}