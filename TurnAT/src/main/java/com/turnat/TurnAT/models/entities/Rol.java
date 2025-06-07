package com.turnat.TurnAT.models.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "Rol")
public class Rol {
	 
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "idRol")
	private int idRol;
	
	private String nombre;
	
	   @ManyToMany(mappedBy = "roles")
	    private Set<Persona> personas;//esta es la q va en sucursal, rol tiene una "lista" de personas
	    //asi se relacionan cachai
	
	
	public Rol() {}
	
	public Rol(String nombre) {
		this.nombre = nombre; 
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", nombre=" + nombre + "]";
	}
	
	

}
