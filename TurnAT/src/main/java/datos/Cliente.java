package datos;

import entities.Direccion;
import entities.Persona;

public class Cliente extends Persona {
	private String telefono;
	
	public Cliente() {}
	public Cliente(String nombre, String apellido, int dni, String email, Direccion direccion){
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni= dni; 
		this.email = email;
		this.direccion = direccion;
	}
	
	
	
}
