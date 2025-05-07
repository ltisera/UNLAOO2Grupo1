package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import negocio.TurnoABM;
import datos.Turno;

public class ConsultasPorIntervaloFechas {
    public static void main(String[] args) {
        TurnoABM abm = new TurnoABM();

        System.out.println("=== Turnos entre dos fechas ===");
        LocalDateTime desde = LocalDateTime.now().minusDays(10);
        LocalDateTime hasta = LocalDateTime.now();
        List<Turno> entreFechas = abm.traerTurnosEntreFechas(desde, hasta);
        entreFechas.forEach(t -> 
            System.out.println(t.getFechaYHora() + " | " + t.getCli().getNombre())
        );

        System.out.println("\n=== Turnos de hoy ===");
        List<Turno> hoy = abm.traerTurnosHoy();
        hoy.forEach(t -> 
            System.out.println(t.getFechaYHora() + " | " + t.getCli().getNombre())
        );

        System.out.println("\n=== Turnos de este mes ===");
        List<Turno> esteMes = abm.traerTurnosEsteMes();
        esteMes.forEach(t -> 
            System.out.println(t.getFechaYHora() + " | " + t.getCli().getNombre())
        );

        System.out.println("\n=== Turnos cancelados última semana ===");
        List<Turno> cancelados = abm.traerTurnosCanceladosUltimaSemana();
        cancelados.forEach(t -> 
            System.out.println(t.getFechaYHora() + " | Estado: " + t.getEst().getDescripcion())
        );
    }
}
