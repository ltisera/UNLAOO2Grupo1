package test;

import java.time.LocalDateTime;
import negocio.TurnoABM;

public class ConsultasPorIntervaloFechas {
    public static void main(String[] args) {
        TurnoABM abm = new TurnoABM();

        int idCliente = 1;
        int idEmpleado = 2;
        int idServicio = 1;
        int idSucursal = 1;
        int estadoPendiente = 1;
        int estadoCancelado = 2;

        System.out.println("=== Agregando turnos ===");

        try {
            abm.agregarTurno(LocalDateTime.now().minusDays(3).withHour(10), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);
            System.out.println("✔ Turno hace 3 días agregado.");
        } catch (Exception e) {
            System.err.println("✖ Error al agregar turno hace 3 días: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            abm.agregarTurno(LocalDateTime.now().withHour(14), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);
            System.out.println("✔ Turno de hoy agregado.");
        } catch (Exception e) {
            System.err.println("✖ Error al agregar turno de hoy: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            abm.agregarTurno(LocalDateTime.now().minusDays(5).withHour(12), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);
            System.out.println("✔ Turno hace 5 días agregado.");
        } catch (Exception e) {
            System.err.println("✖ Error al agregar turno hace 5 días: " + e.getMessage());
            e.printStackTrace();
        }

        int turnoCanceladoId = -1;
        try {
            turnoCanceladoId = abm.agregarTurno(LocalDateTime.now().minusDays(2).withHour(9), idCliente, idEmpleado, idServicio, idSucursal, estadoCancelado);
            System.out.println("✔ Turno cancelado agregado.");
        } catch (Exception e) {
            System.err.println("✖ Error al agregar turno cancelado: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            if (turnoCanceladoId != -1) {
                abm.mostrarDetallesTurno(turnoCanceladoId);
            }
        } catch (Exception e) {
            System.err.println("✖ Error al mostrar detalles del turno cancelado: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=== Fin del test ===");
    }
}
