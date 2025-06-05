package datos;

import java.time.LocalTime;

public class Disponibilidad {
	private int idDisponibilidad;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	public Disponibilidad(int idDisponibilidad, LocalTime horaInicio, LocalTime horaFin) {
		super();
		this.idDisponibilidad = idDisponibilidad;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public int getIdDisponibilidad() {
		return idDisponibilidad;
	}
	public void setIdDisponibilidad(int idDisponibilidad) {
		this.idDisponibilidad = idDisponibilidad;
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
	@Override
	public String toString() {
		return "Disponibilidad [idDisponibilidad=" + idDisponibilidad + ", horaInicio=" + horaInicio + ", horaFin="
				+ horaFin + "]";
	}
	
}
