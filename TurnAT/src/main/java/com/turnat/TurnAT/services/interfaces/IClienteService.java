package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.models.entities.Cliente;

public interface IClienteService {
	
	public Cliente agregar(Cliente c);
	public List<Cliente> traerTodos();
	public void eliminar(Cliente c);
	public void eliminar(int idCliente);
	public Cliente traerPorId(int idCliente);
	public Cliente actualizar(Cliente cliente);
}
