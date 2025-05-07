package test;

import java.time.LocalDateTime;

import negocio.TurnoABM;

public class testTurnoABM {
    public static void main(String[] args) {
        TurnoABM abm = new TurnoABM();
        
        // IDs válidos (deben coincidir con los de tu BD)
        int idCliente = 1;
        int idEmpleado = 2;  // Asegúrate que este empleado existe
        int idServicio = 1;
        int idSucursal = 1;
        int idEstado = 1;    // Estado "Pendiente"
        
        // Crear turno
        int idTurno = abm.agregarTurno(
            LocalDateTime.now().plusHours(2), // Fecha futura
            idCliente, idEmpleado, idServicio, idSucursal, idEstado
        );
        
        if (idTurno != -1) {
            // Mostrar detalles
            abm.mostrarDetallesTurno(idTurno);
            
            // Listar turnos del cliente
            System.out.println("\nTurnos del cliente:");
            abm.traerTurnosPorCliente(idCliente).forEach(t -> 
                abm.mostrarDetallesTurno(t.getIdTurno())
            );
            
            // Cancelar turno
            abm.cancelarTurno(idTurno);
        }
    }
}