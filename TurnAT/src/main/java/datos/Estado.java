package datos;

public class Estado {
	
	private int idEstado;
	private String descripcion; 
	
	public Estado() {}

	public Estado(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstado + ", descripcion=" + descripcion + "]";
	}
	
	
	
}
