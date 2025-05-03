package datos;

public class Empleado extends Persona {
	private int legajo;
	private String cargo;
	
	public Empleado() {}
	public Empleado(String nombre, String apellido, int dni, int legajo, String cargo){
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.legajo = legajo;
		this.cargo = cargo;
	}
	public int getLegajo() {
		return legajo;
	}
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	@Override
	public String toString() {
		return "Empleado [legajo=" + legajo + ", cargo=" + cargo + "]";
	}
	
}
