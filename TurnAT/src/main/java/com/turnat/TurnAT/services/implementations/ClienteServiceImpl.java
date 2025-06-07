package com.turnat.TurnAT.services.implementations;

import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.services.interfaces.IClienteService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{


    private final IClienteRepository clienteRepo;

    public ClienteServiceImpl(IClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    
    @Override
    public Cliente agregar(Cliente cliente) {
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
    
	
}
