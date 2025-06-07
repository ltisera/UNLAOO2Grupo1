package com.turnat.TurnAT.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSucursal")
    private int idSucursal;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Direccion_idDireccion") // nombre de la columna en la tabla 'sucursal'
    private Direccion direccion;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Override
    public String toString() {
        return "Sucursal [idSucursal=" + idSucursal + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + "]";
    }
}
