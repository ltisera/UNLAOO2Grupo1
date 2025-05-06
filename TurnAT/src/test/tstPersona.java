package test;

import dao.PersonaDao;
import datos.Cliente;
import datos.Empleado;


public class tstPersona {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonaDao pdao = new PersonaDao();
		
		//String nombre, String apellido, int dni, String email, int telefono
		Cliente c = new Cliente("Santiago","Serrano", 45823416, "santiislas@outlook.es", 1111111111);
		
		//String nombre, String apellido, int dni, int legajo, String cargo
		Empleado e = new Empleado("Tiago","Serra",30823416, 2121, "Recepcionista");
		
		pdao.agregar(e);	
		pdao.agregar(c);
		System.out.println("POR QUE NO ME AUTO COMPLETA ");
	}

}
