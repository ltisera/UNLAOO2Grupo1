package datos;

public class Cliente extends Persona {
	private String telefono;

	public Cliente() {}

	public Cliente(String nombre, String apellido, int dni, String email, Direccion direccion, String telefono) {
		super(nombre, apellido, dni, email, direccion);
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
		return super.toString() + ", Cliente [telefono=" + telefono + "]";
	}
}
