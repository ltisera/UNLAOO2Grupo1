package datos;

public class Servicio {
	private int idServicio;
	private String nombre;
	private String descripcion;
	
	public Servicio() {}

	public Servicio( String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getIdServicio() {
		return idServicio;
	}

	protected void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Servicio [idServicio=" + idServicio + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
