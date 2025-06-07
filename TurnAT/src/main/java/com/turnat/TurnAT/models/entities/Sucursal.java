package com.turnat.TurnAT.models.entities;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSucursal")
    private int idSucursal;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Direccion_idDireccion") // nombre de la columna en la tabla 'sucursal'
    private Direccion direccion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @ManyToMany(mappedBy = "sucursales")// en el mappedby va el atributo de la clase servicio y esto hace el mapeo bidireccional de muchos a muchos 
    private Set<Servicio> servicios;

    public Sucursal() {}
    public Sucursal(String nombre, String telefono, Direccion direccion) {
    	this.nombre = nombre;
    	this.telefono = telefono;
    	this.direccion = direccion;
    	
    }
    

	public int getIdSucursal() {
		return idSucursal;
	}


	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}


	public Set<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(Set<Servicio> servicios) {
		this.servicios = servicios;
	}
	@Override
	public String toString() {
		return "Sucursal [idSucursal=" + idSucursal + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion="
				+ direccion + "]";
	}
    
    

}
