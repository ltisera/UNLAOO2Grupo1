package com.turnat.TurnAT.dto;

public class SolicitudTurnoDTO {

    private int idServicio;
    private int anio;
    private int mes;
    private int dia;
    private String hora; // formato "HH:mm"
    private int idCliente;

    public SolicitudTurnoDTO(int idServicio, int anio, int mes, int dia, String hora, int idCliente) {
        this.idServicio = idServicio;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.idCliente = idCliente;
    }

    // Getters y Setters
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}