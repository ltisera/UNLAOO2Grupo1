/*
package com.turnat.TurnAT.models.entities;

import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicio")
@Getter
@Setter
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idServicio")
    private int idServicio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "duracion")
    private LocalTime duracion;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idDisponible")
    private Disponible disponibilidad;

	@JoinColumn(name = "Servicio_idServicio") 

    @ManyToMany
    @JoinTable(
        name = "sucursal_servicio", // tabla intermedia
        joinColumns = @JoinColumn(name = "idServicio"),
        inverseJoinColumns = @JoinColumn(name = "idSucursal")
    )
    private Set<Sucursal> sucursales;

    public Servicio(String nombre, String descripcion, LocalTime duracion, Disponible disponibilidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.disponibilidad = disponibilidad;
    }
}
*/