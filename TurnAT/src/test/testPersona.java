package test;

import excepciones.TurnosException;
import negocio.PersonaABM;

public class testPersona {
    public static void main(String[] args) {
        PersonaABM abm = new PersonaABM();
        try {// Si se corre mas de una vez saltara error al intentar agregar
	        abm.agregarCliente("David", "Gonzalez", 111111222, "Jorgi@mail.com", 1135984778);

	        // Agregar cliente
	        abm.agregarCliente("Juan", "Pérez", 12345678, "juan@mail.com", 1122334455);
	        abm.agregarCliente("David", "Gonzalez", 111111222, "Jorgi@mail.com", 1135984778);

	        // Agregar empleado
	        abm.agregarEmpleado("Ana", "Gómez", 87654321, 1001, "Recepcionista");
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }
        
        // Listar todos
        System.out.println("\nTodos los clientes:");
        abm.traerTodosLosClientes().forEach(System.out::println);
        
        System.out.println("\nTodos los empleados:");
        abm.traerTodosLosEmpleados().forEach(System.out::println);
        
        // Buscar por DNI
        System.out.println("\nBuscando cliente con DNI 12345678:");
        System.out.println(abm.traerClientePorDni(12345678));
    }
}