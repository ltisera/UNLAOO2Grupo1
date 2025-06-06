package com.turnat.TurnAT.models.entities;

public class Cliente extends Persona {
	private String telefono;
	
	public Cliente() {}
	public Cliente(String nombre, String apellido, int dni, String email, Direccion direccion, String telefono){
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni= dni; 
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return "Cliente [telefono=" + telefono + "]";
	}
	
	
	
}
