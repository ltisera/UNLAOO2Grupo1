package com.turnat.TurnAT.dto;

import com.turnat.TurnAT.models.entities.Sucursal;

public class SucursalDTO {
    private int idSucursal;
    private String nombre;
    private String telefono;
    private DireccionDTO direccion;

    public SucursalDTO() {
    }

    public SucursalDTO(Sucursal sucursal) {
        this.idSucursal = sucursal.getIdSucursal();
        this.nombre = sucursal.getNombre();
        this.telefono = sucursal.getTelefono();
        if (sucursal.getDireccion() != null) {
            this.direccion = new DireccionDTO(sucursal.getDireccion().getLocalidad(),sucursal.getDireccion().getCalle(),sucursal.getDireccion().getAltura(),sucursal.getDireccion().getDepto());
        }
    }

    // getters y setters

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int id) {
        this.idSucursal = id;
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

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }
}