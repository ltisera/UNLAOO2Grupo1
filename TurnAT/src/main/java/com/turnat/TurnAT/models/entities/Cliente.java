package com.turnat.TurnAT.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "Cliente_idPersona")
public class Cliente extends Persona {
	private String telefono;
	
	public Cliente(String nombre, String password, String apellido, int dni, String email, Direccion direccion, String telefono){
		super(nombre, password, apellido, dni, email, direccion); // 👈 usamos el constructor de Persona
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return "Cliente [telefono=" + telefono + "]";
	}
	
	
	
}
