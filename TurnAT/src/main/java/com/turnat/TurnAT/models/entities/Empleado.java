package com.turnat.TurnAT.models.entities;

import com.turnat.TurnAT.models.entities.Servicio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "Persona_idPersona")
public class Empleado extends Persona {
	
	private String legajo;
	@ManyToOne( fetch = FetchType.LAZY)//saco el cascade por q al crear un empleado quiere crear un servicio pero en realidad ya le pasamos un servicio existente
	@JoinColumn(name = "Servicio_idServicio") 
	private Servicio servicio;
	
	public Empleado() {
		
	}
	public Empleado(String nombre, String password, String apellido, int dni, String email, Direccion direccion,String legajo, Servicio servicio) {
		super(nombre, password, apellido, dni, email, direccion); // 👈 usamos el constructor de Persona
		this.legajo = legajo;
		this.servicio = servicio;
	}

	
	public String getLegajo() {
		return legajo;
	}


	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}


	@Override
	public String toString() {
		return "Empleado [legajo=" + legajo + ", servicio=" + servicio + "]";
	}
	
	
	
}