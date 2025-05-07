package negocio;


import dao.PersonaDao;
import datos.Cliente;
import datos.Empleado;
import datos.Persona;
import java.util.List;
import excepciones.TurnosException;

public class PersonaABM {
    private PersonaDao dao = new PersonaDao();

    // -------------------- Métodos genéricos para Persona --------------------
    
    public int agregarPersona(Persona persona) {
        if (persona instanceof Cliente) {
            return agregarCliente((Cliente) persona);
        } else if (persona instanceof Empleado) {
            return agregarEmpleado((Empleado) persona);
        }
        return dao.agregar(persona);
    }

    public void actualizarPersona(Persona persona) {
        if (existePersona(persona.getIdPersona())) {
            dao.actualizar(persona);
            System.out.println("✅ Persona actualizada correctamente");
        } else {
        	throw new TurnosException("⚠️ No existe la persona con ID: " + persona.getIdPersona());
        }
    }

    public void eliminarPersona(int idPersona) {
        Persona persona = dao.traer(idPersona);
        if (persona != null) {
            dao.eliminar(persona);
            System.out.println("✅ Persona eliminada correctamente");
        } else {
        	throw new TurnosException("⚠️ No existe la persona con ID: " + idPersona);
        }
    }

    public Persona traerPersona(int idPersona) {
        return dao.traer(idPersona);
    }

    public List<Persona> traerTodasLasPersonas() {
        return dao.traer();
    }

    // -------------------- Métodos específicos para Cliente --------------------
    
    public int agregarCliente(String nombre, String apellido, int dni, String email, int telefono) {
        if (dao.traerClientePorDni(dni) != null) {
        	throw new TurnosException("⚠️ Ya existe un cliente con DNI: " + dni);
        }
        Cliente cliente = new Cliente(nombre, apellido, dni, email, telefono);
        int id = dao.agregar(cliente);
        System.out.println("✅ Cliente agregado con ID: " + id);
        return id;
    }

    public int agregarCliente(Cliente cliente) {
        return agregarCliente(cliente.getNombre(), cliente.getApellido(), cliente.getDni(), 
                            cliente.getEmail(), cliente.getTelefono());
    }

    public Cliente traerCliente(int idCliente) {
        return dao.traerCliente(idCliente);
    }

    public List<Cliente> traerTodosLosClientes() {
        return dao.traerClientes();
    }

    public Cliente traerClientePorDni(int dni) {
        return dao.traerClientePorDni(dni);
    }

    // -------------------- Métodos específicos para Empleado --------------------
    
    public int agregarEmpleado(String nombre, String apellido, int dni, int legajo, String cargo) {
        Empleado existente = traerEmpleadoPorDni(dni);
        if (existente != null) {
        	throw new TurnosException("⚠️ Ya existe un empleado con DNI: " + dni); 
        }
        Empleado empleado = new Empleado(nombre, apellido, dni, legajo, cargo);
        int id = dao.agregar(empleado);
        System.out.println("✅ Empleado agregado con ID: " + id);
        return id;
    }

    public int agregarEmpleado(Empleado empleado) {
        return agregarEmpleado(empleado.getNombre(), empleado.getApellido(), empleado.getDni(),
                             empleado.getLegajo(), empleado.getCargo());
    }

    public Empleado traerEmpleado(int idEmpleado) {
        return dao.traerEmpleado(idEmpleado);
    }

    public List<Empleado> traerTodosLosEmpleados() {
        return dao.traerEmpleados();
    }

    public Empleado traerEmpleadoPorDni(int dni) {
        List<Empleado> empleados = dao.traerEmpleados();
        return empleados.stream()
                .filter(e -> e.getDni() == dni)
                .findFirst()
                .orElse(null);
    }

    public List<Empleado> traerEmpleadoPorCargo(String cargo) {
        return dao.traerEmpleadoPorCargo(cargo);
    }

    // -------------------- Métodos auxiliares --------------------
    
    private boolean existePersona(int idPersona) {
        return dao.traer(idPersona) != null;
    }
}