package com.turnat.TurnAT.services.interfaces;

import java.util.List;


import com.turnat.TurnAT.models.entities.Sucursal;

public interface ISucursalService {

	public Sucursal agregar(Sucursal s);
	public void eliminar(Sucursal s);
	public void eliminar(int idSucursal);
	public Sucursal traerPorId(int idSucursal);
	public List<Sucursal> traerTodos();
	
}
