package com.turnat.TurnAT.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.Empleado;
import com.turnat.TurnAT.repositories.IEmpleadoRepository;
import com.turnat.TurnAT.services.interfaces.IEmpleadoService;

import jakarta.transaction.Transactional;
@Service
public class EmpleadoServiceImp implements IEmpleadoService{
	
	private final IEmpleadoRepository repoEmpleado;
	
	
	public EmpleadoServiceImp(IEmpleadoRepository repoEmpleado) {
		super();
		this.repoEmpleado = repoEmpleado;
	}
	public Empleado agregar(Empleado e) {
		return repoEmpleado.save(e);
	}
	public List<Empleado> traerTodos(){
		return repoEmpleado.findAll();
	}
	public void eliminar(Empleado e) {
		repoEmpleado.deleteById(e.getIdPersona());
	}
	public void eliminar(int idEmpleado) {
		repoEmpleado.deleteById(idEmpleado);
	}
	public Empleado traerPorId(int idEmpleado) {
		return repoEmpleado.findById(idEmpleado).orElseThrow(() -> new RuntimeException("Empleade no encontrade"));
	}
	
	@Transactional // Esto lo actualiza si el ID existe
	public Empleado actualizar(Empleado empleado) {
		return repoEmpleado.save(empleado);
	}
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;

	    public void registrarEmpleado(Empleado empleado) {
	        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
	        repoEmpleado.save(empleado);
	    }
	
}
