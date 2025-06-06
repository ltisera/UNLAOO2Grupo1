/*package com.turnat.TurnAT.models.entities;

import com.turnat.TurnAT.models.entities.Servicio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "Persona_idPersona")
public class Empleado {
	
	private String legajo;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "Servicio_idServicio") 
	private Servicio servicio;
	
	
	public Empleado(String legajo, Servicio servicio) {
		
	}
	

	
}
*/