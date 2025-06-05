package datos;

public class Direccion {
	private int idDireccion;
	private String localidad;
	private String calle;
	private int altura;
	private String depto;
	
	public Direccion() {}

	public Direccion(int idDireccion, String localidad, String calle, int altura) {
		super();
		this.idDireccion = idDireccion;
		this.localidad = localidad;
		this.calle = calle;
		this.altura = altura;
	}

	public Direccion(int idDireccion, String localidad, String calle, int altura, String depto) {
		super();
		this.idDireccion = idDireccion;
		this.localidad = localidad;
		this.calle = calle;
		this.altura = altura;
		this.depto = depto;
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	@Override
	public String toString() {
		if(depto!=null) {
			return "Direccion [idDireccion=" + idDireccion + ", localidad=" + localidad + ", calle=" + calle + ", altura="
					+ altura + ", depto=" + depto + "]";
		}
		return "Direccion [idDireccion=" + idDireccion + ", localidad=" + localidad + ", calle=" + calle + ", altura="
				+ altura + "]";
	}

	
}
