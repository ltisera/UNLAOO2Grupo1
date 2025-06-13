package com.turnat.TurnAT.dto;

import com.turnat.TurnAT.models.entities.Direccion;

public class DireccionDTO {
    private String localidad;
    private String calle;
    private Integer altura;
    private String depto;

    public DireccionDTO() {
    }

    public DireccionDTO(Direccion direccion) {
        this.localidad = direccion.getLocalidad();
        this.calle = direccion.getCalle();
        this.altura = direccion.getAltura();
        this.depto = direccion.getDepto();
    }

    // getters y setters

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

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }
}