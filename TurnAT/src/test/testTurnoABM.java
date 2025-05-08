package test;

import java.time.LocalDateTime;

import excepciones.TurnosException;
import negocio.TurnoABM;

public class testTurnoABM {
    public static void main(String[] args) {
        TurnoABM abm = new TurnoABM();

        int idCliente = 3;//verificar en bd los id
        int idEmpleado = 2;//verificar en bd los id
        int idServicio = 1;
        int idSucursal = 1;
        int estadoPendiente = 1;
        int estadoCancelado = 2;

        System.out.println("=== Agregando turnos ===");
        try {
	        // Turno en el pasado reciente (dentro de los últimos 10 días)
	        abm.agregarTurno(LocalDateTime.now().minusDays(3).withHour(10), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);
	
	        // Turno de hoy
	        abm.agregarTurno(LocalDateTime.now().withHour(14), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);
	
	        // Turno del mes (hace unos días)
	        abm.agregarTurno(LocalDateTime.now().minusDays(5).withHour(12), idCliente, idEmpleado, idServicio, idSucursal, estadoPendiente);
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }
        // Turno cancelado hace 2 días
        int turnoCanceladoId = abm.agregarTurno(LocalDateTime.now().minusDays(2).withHour(9), idCliente, idEmpleado, idServicio, idSucursal, estadoCancelado);

        System.out.println("=== Listo. Verifica con tus consultas ===");

        // Mostramos detalles del cancelado
        abm.mostrarDetallesTurno(turnoCanceladoId);
        
        try {
        	System.out.println("Trer por rango de fechas y cliente");
        	LocalDateTime desde = LocalDateTime.of(2023, 5, 6, 0, 0); // 6 de mayo de 2023 a las 00:00
        	LocalDateTime hasta = LocalDateTime.of(2026, 5, 6, 23, 59); // 6 de mayo de 2026 a las 23:59
        	System.out.println(abm.traerPorRangoFechasYCliente(desde , hasta, 2));
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }
        try {
        	System.out.println("Trer por rango de fechas y estado");
        	LocalDateTime desde = LocalDateTime.of(2023, 5, 6, 0, 0); // 6 de mayo de 2023 a las 00:00
        	LocalDateTime hasta = LocalDateTime.of(2026, 5, 6, 23, 59); // 6 de mayo de 2026 a las 23:59
        	System.out.println(abm.traerPorRangoFechasYEstado(desde , hasta, 1));
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }
        try {
        	System.out.println("Trer por rango de fechas y empleado");
        	LocalDateTime desde = LocalDateTime.of(2023, 5, 6, 0, 0); // 6 de mayo de 2023 a las 00:00
        	LocalDateTime hasta = LocalDateTime.of(2026, 5, 6, 23, 59); // 6 de mayo de 2026 a las 23:59
        	System.out.println(abm.traerPorRangoFechasYEmpleado(desde , hasta, 1));
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }
        try {
        	System.out.println("Trer por rango de fechas y servicio");
        	LocalDateTime desde = LocalDateTime.of(2023, 5, 6, 0, 0); // 6 de mayo de 2023 a las 00:00
        	LocalDateTime hasta = LocalDateTime.of(2026, 5, 6, 23, 59); // 6 de mayo de 2026 a las 23:59
        	System.out.println(abm.traerPorRangoFechasYServicio(desde , hasta, 1));
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }
    }
}
