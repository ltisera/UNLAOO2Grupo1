package com.turnat.TurnAT.services.implementations;

import java.util.List;
import org.springframework.stereotype.Service;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.repositories.ISucursalRepository;
import com.turnat.TurnAT.services.interfaces.ISucursalService;

import jakarta.transaction.Transactional;

@Service
public class SucursalServiceImp implements ISucursalService{
	
	private final ISucursalRepository sucursalRepo;

   public SucursalServiceImp(ISucursalRepository sucursalRepo) {
	   this.sucursalRepo = sucursalRepo;
   }
	
    
    @Override
    public Sucursal agregar(Sucursal sucursal) {
        return sucursalRepo.save(sucursal);
    }
    
    @Override
    public List<Sucursal> traerTodos() {
        return sucursalRepo.findAll();
    }

    @Override
    public void eliminar(int idSucursal) {
        sucursalRepo.deleteById(idSucursal);
    }
    
    @Override
    public void eliminar(Sucursal suc) {
        sucursalRepo.deleteById(suc.getIdSucursal());
    }
    public Sucursal traerPorId(int idSucursal) {
    	return sucursalRepo.findById(idSucursal).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }
    
    @Transactional // Esto lo actualiza si el ID existe
    public Sucursal actualizar(Sucursal sucursal) {
    	return sucursalRepo.save(sucursal);
    	
    }

}
