package datos;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona {
	private int legajo;
	private String cargo;
	private List<Servicio> servicios = new ArrayList<>();

	public Empleado() {}

	public Empleado(String nombre, String apellido, int dni, String email, Direccion direccion, int legajo, String cargo) {
		super(nombre, apellido, dni, email, direccion);
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

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void agregarServicio(Servicio servicio) {
		servicios.add(servicio);
	}

	@Override
	public String toString() {
		return super.toString() + ", Empleado [legajo=" + legajo + ", cargo=" + cargo + ", servicios=" + servicios + "]";
	}
}
