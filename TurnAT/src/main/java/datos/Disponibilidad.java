package datos;

import java.time.LocalTime;

public class Disponibilidad {
    private int idDisponibilidad;
    private int diaSemana; // 0=Domingo, ..., 6=Sábado
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Disponibilidad(int idDisponibilidad, int diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.idDisponibilidad = idDisponibilidad;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(int idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    
    public static String nombreDia(int diaSemana) {
        String[] dias = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        return (diaSemana >= 0 && diaSemana <= 6) ? dias[diaSemana] : "Desconocido";
    }

    @Override
    public String toString() {
        return "Disponibilidad [id=" + idDisponibilidad + ", día=" + nombreDia(diaSemana) +
               ", desde=" + horaInicio + ", hasta=" + horaFin + "]";
    }
}
