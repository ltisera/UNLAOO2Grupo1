package test;

import java.time.LocalDateTime;
import negocio.TurnoABM;

public class testTurnoABM {
    public static void main(String[] args) {
        TurnoABM abm = new TurnoABM();

        int idCliente = 1;
        int idEmpleado = 2;
        int idServicio = 1;
        int idSucursal = 1;
        int estadoPendiente = 1;
        int estadoCancelado = 2;

        System.out.println("=== Agregando turnos ===");

        // Turno en el pasado reciente (dentro de los últimos 10 días)
        abm.agregarTurno(LocalDateTime.now().minusDays(3).withHour(10), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);

        // Turno de hoy
        abm.agregarTurno(LocalDateTime.now().withHour(14), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);

        // Turno del mes (hace unos días)
        abm.agregarTurno(LocalDateTime.now().minusDays(5).withHour(12), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);

        // Turno cancelado hace 2 días
        int turnoCanceladoId = abm.agregarTurno(LocalDateTime.now().minusDays(2).withHour(9), idCliente, idEmpleado, idServicio, idSucursal, estadoCancelado);

        System.out.println("=== Listo. Verifica con tus consultas ===");

        // Mostramos detalles del cancelado
        abm.mostrarDetallesTurno(turnoCanceladoId);
    }
}
