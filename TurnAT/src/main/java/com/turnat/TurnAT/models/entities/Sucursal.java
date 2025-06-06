package com.turnat.TurnAT.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Sucursal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSucursal")
    private Integer idSucursal;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "telefono", nullable = false, length = 45)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "Direccion_idDireccion", nullable = false)
    private Direccion direccion;
}
