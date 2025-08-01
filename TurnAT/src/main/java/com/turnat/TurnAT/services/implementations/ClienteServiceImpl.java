package com.turnat.TurnAT.services.implementations;

import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.services.interfaces.IClienteService;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.exceptions.DniDuplicadoException;
import com.turnat.TurnAT.models.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{


    private final IClienteRepository clienteRepo;

    public ClienteServiceImpl(IClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    
    @Override
    public Cliente agregar(Cliente cliente) {
        // Verificar si el DNI ya existe
        if (clienteRepo.findByDni(cliente.getDni()).isPresent()) {
            throw new DniDuplicadoException("El DNI ya está registrado en el sistema.");
        }
        return clienteRepo.save(cliente);
    }

    
    @Override
    public List<Cliente> traerTodos() {
        return clienteRepo.findAll();
    }

    @Override
    public void eliminar(int idCliente) {
        clienteRepo.deleteById(idCliente);
    }
    
    @Override
    public void eliminar(Cliente cli) {
        clienteRepo.deleteById(cli.getIdPersona());
    }
    
    public Cliente traerPorId(int idCliente) {
    	return clienteRepo.findById(idCliente).orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
    	
    }
   
    @Transactional // Esto lo actualiza si el ID existe
    public Cliente actualizar(Cliente cliente) {
        return clienteRepo.save(cliente); 
    }
    
    
    public Cliente buscarPorMail(String email) {
		// TODO Auto-generated method stub
		return clienteRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Cliente No Entroncado"));
	}











	@Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarEmpleado(Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        clienteRepo.save(cliente);
    }
    
	
}
