package com.turnat.TurnAT.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Direccion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDireccion")
	private int idDireccion;
	private String localidad;
	private String calle;
	private int altura;
	private String depto;
	
	public Direccion() {}

	public Direccion(String localidad, String calle, int altura) {
		super();
		this.localidad = localidad;
		this.calle = calle;
		this.altura = altura;
	}

	public Direccion(String localidad, String calle, int altura, String depto) {
		super();
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
			return  localidad + ", " + calle + ", "
					+ altura + ", depto " + depto+".";
		}
		return localidad + ", " + calle + ", "
				+ altura +".";
	}

	
}
