package datos;

import java.time.LocalTime;

public class Servicio {
	private int idServicio;
	private String nombre;
	private String descripcion;
	private LocalTime duracion;
	
    // Constructor vacío
	public Servicio() {}

    // Constructor con parámetros
	public Servicio(int idServicio, String nombre, String descripcion, LocalTime duracion) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }
	
    // Constructor sin id (para cuando se genera automáticamente)
	public Servicio(String nombre, String descripcion, LocalTime duracion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

	// Getters y Setters
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
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

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }
	
	@Override
	public String toString() {
		return "Servicio [idServicio=" + idServicio + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

}
