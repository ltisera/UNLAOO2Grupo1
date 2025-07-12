package com.turnat.TurnAT.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IDireccionService;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@Controller
@RequestMapping("/admin/cliente")
public class AdminClienteController {
	
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IDireccionService direccionService;
	@Autowired
	private ITurnoService turnoService;
	
	// Mostrar listado de clientes
    @GetMapping("/listado")
    public String listarClientes(Model model) {
        List<Cliente> clientes= clienteService.traerTodos();
        model.addAttribute("clientes", clientes);
        return "clienteAdminListado"; // Vista lista de clientes
    }
    
    // Mostrar formulario para editar 
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model) {
        Cliente cliente= clienteService.traerPorId(id);
        model.addAttribute("cliente", cliente);

        return "clienteAdminEditar"; // Vista Thymeleaf para editar (crear)
    }
    
    @PostMapping
    public String actulizarCliente (@PathVariable("id") int id,  @ModelAttribute Cliente cliente) {
    	Cliente existente = clienteService.traerPorId(id);
    	
    	existente.setNombre(cliente.getNombre());
    	existente.setApellido(cliente.getApellido());
    	existente.setDni(cliente.getDni());
    	existente.setTelefono(cliente.getTelefono());
    	
    	//persistimos
    	Direccion nuevaDireccion = existente.getDireccion();
    	 if (nuevaDireccion == null) {
             nuevaDireccion = new Direccion();
         }

         nuevaDireccion.setLocalidad(cliente.getDireccion().getLocalidad());
         nuevaDireccion.setCalle(cliente.getDireccion().getCalle());
         nuevaDireccion.setAltura(cliente.getDireccion().getAltura());
         nuevaDireccion.setDepto(cliente.getDireccion().getDepto());

         direccionService.actualizar(nuevaDireccion); // persistir dirección actualizada
         existente.setDireccion(nuevaDireccion);
         clienteService.actualizar(existente);
         
    	return "redirect:/admin/cliente/listado" ;
    }
    
    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") int id) {
       
    	List<Turno> turnos = turnoService.traerPorIdCliente(id);
    	turnoService.eliminarTodos(turnos);

    	
    	clienteService.eliminar(id);
        return "redirect:/admin/cliente/listado";
    }

	
}
