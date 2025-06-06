package com.turnat.datos;

import java.util.ArrayList;
import java.util.List;

import com.turnat.entities.Direccion;

public class Sucursal {
    private int idSucursal;
    private String nombre;
    private Direccion direccion;
    private int telefono;
    private List<Servicio> servicios;  // Lista de servicios que brinda la sucursal
    
    public Sucursal() {
        this.servicios = new ArrayList<>();  // Inicialización en constructor vacío
    }

    public Sucursal(int idSucursal, String nombre, Direccion direccion, int telefono) {
        this();  // Llama al constructor vacío para inicializar la lista
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Métodos para manejar la lista de servicios
    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
    
    public void agregarServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }
    
    public void removerServicio(Servicio servicio) {
        this.servicios.remove(servicio);
    }

    // Resto de getters y setters...
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Sucursal [idSucursal=" + idSucursal + ", nombre=" + nombre + 
               ", direccion=" + direccion + ", telefono=" + telefono + 
               ", servicios=" + servicios + "]";
    }
}