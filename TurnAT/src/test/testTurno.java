package test;

import java.time.LocalDateTime;
import negocio.TurnoABM;

public class testTurno {
    public static void main(String[] args) {
        TurnoABM abm = new TurnoABM();
        
        try {
            // Agregar un turno (IDs ficticios)
            int idTurno = abm.agregar(
                LocalDateTime.of(2023, 10, 25, 14, 30),
                1,  // ID Cliente
                1,  // ID Empleado
                1,  // ID Servicio
                1,  // ID Sucursal
                1   // ID Estado (1 = "Pendiente")
            );
            System.out.println("Turno creado con ID: " + idTurno);

            // Listar turnos de un empleado
            System.out.println("Turnos del empleado 1: " + abm.traerTurnosPorEmpleado(1));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}