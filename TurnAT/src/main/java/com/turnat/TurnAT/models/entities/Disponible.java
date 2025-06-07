
package com.turnat.TurnAT.models.entities;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disponible")
@Getter
@Setter
@NoArgsConstructor
public class Disponible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDisponibilidad")
    private int idDisponibilidad;

    @Column(name = "horaInicio")
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    @Column(name = "lunes", columnDefinition = "TINYINT")
    private boolean lunes;

    @Column(name = "martes", columnDefinition = "TINYINT")
    private boolean martes;

    @Column(name = "miercoles", columnDefinition = "TINYINT")
    private boolean miercoles;

    @Column(name = "jueves", columnDefinition = "TINYINT")
    private boolean jueves;

    @Column(name = "viernes", columnDefinition = "TINYINT")
    private boolean viernes;

    @Column(name = "sabado", columnDefinition = "TINYINT")
    private boolean sabado;

    @Column(name = "domingo", columnDefinition = "TINYINT")
    private boolean domingo;

    public Disponible(LocalTime horaInicio, LocalTime horaFin,
                      boolean lunes, boolean martes, boolean miercoles,
                      boolean jueves, boolean viernes, boolean sabado, boolean domingo) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
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


	public boolean isLunes() {
		return lunes;
	}


	public void setLunes(boolean lunes) {
		this.lunes = lunes;
	}


	public boolean isMartes() {
		return martes;
	}


	public void setMartes(boolean martes) {
		this.martes = martes;
	}


	public boolean isMiercoles() {
		return miercoles;
	}


	public void setMiercoles(boolean miercoles) {
		this.miercoles = miercoles;
	}


	public boolean isJueves() {
		return jueves;
	}


	public void setJueves(boolean jueves) {
		this.jueves = jueves;
	}


	public boolean isViernes() {
		return viernes;
	}


	public void setViernes(boolean viernes) {
		this.viernes = viernes;
	}


	public boolean isSabado() {
		return sabado;
	}


	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}


	public boolean isDomingo() {
		return domingo;
	}


	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}


	@Override
    public String toString() {
        return "Disponible [idDisponibilidad=" + idDisponibilidad +
               ", horaInicio=" + horaInicio +
               ", horaFin=" + horaFin +
               ", lunes=" + lunes +
               ", martes=" + martes +
               ", miercoles=" + miercoles +
               ", jueves=" + jueves +
               ", viernes=" + viernes +
               ", sabado=" + sabado +
               ", domingo=" + domingo + "]";
    }
}
