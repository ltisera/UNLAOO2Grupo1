package com.turnat.TurnAT.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTurno")
    private int idTurno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cliente_Cliente_idPersona")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Servicio_idServicio")
    private Servicio servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estado_idEstado")
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FechaYHora_idFechaYHora")
    private FechaYHora fechaYHora;

    public Turno() {}

    public Turno(Cliente cliente, Servicio servicio, Estado estado, FechaYHora fechaYHora) {
        this.cliente = cliente;
        this.servicio = servicio;
        this.estado = estado;
        this.fechaYHora = fechaYHora;
    }

    // Getters y Setters
    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public FechaYHora getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(FechaYHora fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", cliente=" + cliente + ", servicio=" + servicio + ", estado=" + estado
				+ ", fechaYHora=" + fechaYHora + "]";
	}
    
}
