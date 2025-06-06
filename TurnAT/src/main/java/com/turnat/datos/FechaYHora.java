package com.turnat.datos;

import java.time.LocalDate;
import java.time.LocalTime;

public class FechaYHora {

	private int idFechaHora;
	private LocalDate fecha;
	private LocalTime hora;
	
	public FechaYHora(int idFechaHora, LocalDate fecha, LocalTime hora) {
		super();
		this.idFechaHora = idFechaHora;
		this.fecha = fecha;
		this.hora = hora;
	}
	public int getIdFechaHora() {
		return idFechaHora;
	}
	public void setIdFechaHora(int idFechaHora) {
		this.idFechaHora = idFechaHora;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	@Override
	public String toString() {
		return "FechaYHora [idFechaHora=" + idFechaHora + ", fecha=" + fecha + ", hora=" + hora + "]";
	}
	
	
}
