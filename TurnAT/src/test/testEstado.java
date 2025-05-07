package test;

import excepciones.TurnosException;
import negocio.EstadoABM;

public class testEstado {
    public static void main(String[] args) {
        EstadoABM abm = new EstadoABM();

        try {
	        System.out.println("=== Agregando estados básicos ===");
	        abm.agregar("Pendiente");
	        abm.agregar("Cancelado por cliente");
	        abm.agregar("Disponible");
        }catch(TurnosException e){
        	System.out.println(e.getMessage());
        }
        try {
        	System.out.println("\n=== Intentando agregar duplicados ===");
            abm.agregar("Pendiente"); // Mostrará advertencia
        }catch(TurnosException e){
        	System.out.println(e.getMessage());
        }
       

        System.out.println("\n=== Listado completo ===");
        abm.traerTodos().forEach(e -> 
            System.out.println("ID " + e.getIdEstado() + ": " + e.getDescripcion())
        );
        try {
	        System.out.println("\n=== Actualizando estado ===");
	        abm.actualizar(2, "Cancelado por sistema"); // Cambiar descripción
	        abm.actualizar(2, "Pendiente"); // Intentar cambiar a descripción existente
        }catch(TurnosException e){
        	System.out.println(e.getMessage());
        }
        
    }
}