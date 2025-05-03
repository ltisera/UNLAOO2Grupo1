package datos;

public class Cliente extends Persona{
	
	private String email;
	private int telefono;
	
	public Cliente() {}
	public Cliente(String nombre, String apellido, int dni, String email, int telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Cliente [email=" + email + ", telefono=" + telefono + "]";
	}
	

}
