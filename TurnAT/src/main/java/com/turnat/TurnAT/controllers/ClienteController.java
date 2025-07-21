package com.turnat.TurnAT.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Rol;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IRolRepository;
import com.turnat.TurnAT.services.implementations.DireccionServiceImp;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IEmailService;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteRepository clienteRepository;
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IRolRepository rolRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IEmailService emailService;
	@Autowired
	private IServicioService servicioService;

	@GetMapping("/registro-cliente")
	public String mostrarFormulario(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "registroCliente";
	}

	@PostMapping("/registro-cliente")
	public String registroCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
		// validacion de q no exista alguien con ese mail
		Optional<Cliente> existente = clienteRepository.findByEmail(cliente.getEmail());
		if (existente.isPresent()) {
			model.addAttribute("error", "Ya existe agluien registrado con este email.");
			return "registroCliente";
		}

		cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
		// Asignar rol cliente
		Rol rol = rolRepository.findByNombre("CLIENTE").orElseThrow(() -> new RuntimeException("Rol no encontrado"));
		Set<Rol> roles = new HashSet<>(); // se crea el set de rol
		roles.add(rol);
		cliente.setRoles(roles);

		clienteService.agregar(cliente);
		// envio de mail de registro
		String asunto = "¡Te regitraste en TurnAT!";
		String cuerpo = "Hola " + cliente.getNombre()
				+ ",\n\nGracias por registrarte en TurnAT.\n\nSaludos,\nEl equipo de TurnAT";
		emailService.enviarCorreo(cliente.getEmail(), asunto, cuerpo);
		return "redirect:/login";

	}

	@GetMapping("/indexCliente")
	@PostMapping("/indexCliente")
	public String clienteInicio() {
		return "indexCliente";
	}

	@GetMapping("/clienteDatos")
	public String misDatos(Model model, Authentication authentication) {
		// Obtener el mail del usuario logueado
		String email = authentication.getName();

		// Buscar el cliente en la base de datos
		Cliente cliente = clienteRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

		// Pasarlo al modelo
		model.addAttribute("cliente", cliente);

		System.out.println("mapeo cliente datos");
		return "clienteDatos";
	}

	@GetMapping("/agregarDireccion")
	public String mostrarFormularioDireccion(Model model, Principal principal) {
		model.addAttribute("direccion", new Direccion());
		return "agregarDireccionCliente";
	}

	// Guardar dirección
	@PostMapping("/agregarDireccion")
	public String guardarDireccion(@ModelAttribute Direccion direccion, Principal principal) {
		// el metodo principal.getName() te devuelve por defecto el username del
		// usuario autenticado, que en nuestro caso sería el email
		Cliente cliente = clienteRepository.findByEmail(principal.getName())
				.orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado"));

		cliente.setDireccion(direccion);// se crea la direccion automaticamente al guardarse en cliente

		clienteService.actualizar(cliente);

		return "redirect:/cliente/clienteDatos";
	}

	@Autowired
	private ITurnoService turnoService;

	@GetMapping("/clienteTurnos")
	public String misTurnos(Model model, Authentication authentication) {

		// Obtener el mail del usuario logueado
		String email = authentication.getName();

		// Buscar el cliente en la base de datos
		Cliente cliente = clienteRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

		List<Turno> turnos = turnoService.traerPorIdCliente(cliente.getIdPersona());

		model.addAttribute("turnosCliente", turnos);

		return "clienteMisTurnos";

	}

	@GetMapping("/solicitarTurno")
	public String mostrarFormularioTurno(Model model, Authentication auth) {
		// Obtener email del cliente logueado
		String email = auth.getName();

		// Buscar cliente por email
		Cliente cliente = clienteRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado")); //EXCeP

		// Agregar ID al modelo
		model.addAttribute("idCliente", cliente.getIdPersona());

		return "solicitarTurno"; // renderiza solicitarTurno.html
	}
}
