package com.turnat.TurnAT.models.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "fechaYHora")
public class FechaYHora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFechaYHora")
    private int idFechaYHora;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name = "Hora")
    private LocalTime hora;

    public FechaYHora() {}

    public FechaYHora(LocalDate fecha, LocalTime hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y Setters
    public int getIdFechaYHora() {
        return idFechaYHora;
    }

    public void setIdFechaYHora(int idFechaYHora) {
        this.idFechaYHora = idFechaYHora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
