package com.turnat.TurnAT.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	
	@Column(name = "idEstado")
	private int idEstado;
	@Column(name = "descripcion")
	private String descripcion;
	
	
	public Estado() {
        // Constructor vacío requerido por JPA
    }
	
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
	
	
	
	
	

}
