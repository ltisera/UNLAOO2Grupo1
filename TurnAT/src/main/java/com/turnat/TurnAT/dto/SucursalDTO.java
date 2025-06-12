package com.turnat.TurnAT.dto;
import com.turnat.TurnAT.models.entities.Sucursal;


public class SucursalDTO {
    private int id;
    private String nombre;
    private String localidad; 

    public SucursalDTO(Sucursal sucursal) {
        this.id = sucursal.getIdSucursal();
        this.nombre = sucursal.getNombre();
        //this.direccion = sucursal.getDireccion().getCalle();
        this.localidad = sucursal.getDireccion().getLocalidad();
        
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



}