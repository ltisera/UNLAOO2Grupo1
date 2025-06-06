/*
package com.turnat.TurnAT.models.entities;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disponible")
@Getter
@Setter
@NoArgsConstructor
public class Disponible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDisponibilidad")
    private int idDisponibilidad;

    @Column(name = "horaInicio")
    private LocalTime horaInicio;

    @Column(name = "horaFin")
    private LocalTime horaFin;

    @Column(name = "lunes")
    private boolean lunes;

    @Column(name = "martes")
    private boolean martes;

    @Column(name = "miercoles")
    private boolean miercoles;

    @Column(name = "jueves")
    private boolean jueves;

    @Column(name = "viernes")
    private boolean viernes;

    @Column(name = "sabado")
    private boolean sabado;

    @Column(name = "domingo")
    private boolean domingo;

    public Disponible(LocalTime horaInicio, LocalTime horaFin,
                      boolean lunes, boolean martes, boolean miercoles,
                      boolean jueves, boolean viernes, boolean sabado, boolean domingo) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    @Override
    public String toString() {
        return "Disponible [idDisponibilidad=" + idDisponibilidad +
               ", horaInicio=" + horaInicio +
               ", horaFin=" + horaFin +
               ", lunes=" + lunes +
               ", martes=" + martes +
               ", miercoles=" + miercoles +
               ", jueves=" + jueves +
               ", viernes=" + viernes +
               ", sabado=" + sabado +
               ", domingo=" + domingo + "]";
    }
}
*/