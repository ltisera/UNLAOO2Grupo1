package test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.EstadoDao;
import dao.PersonaDao;
import dao.ServicioDao;
import dao.SucursalDao;
import dao.TurnoDao;

import negocio.EstadoABM;
import negocio.SucursalABM;
import negocio.TurnoABM;

import datos.Servicio;
import datos.Cliente;
import datos.Sucursal;
import datos.Turno;
import datos.Empleado;
import datos.Estado;
import datos.Persona;



public class testTraerTodo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Traer Objetos Separados por DAOs");
		System.out.println("Traemos servicio id=1");
		ServicioDao serv = new ServicioDao();
		Servicio unServicio = serv.traer(1);
		System.out.println(unServicio.toString());
		
		System.out.println("Traemos sucursal id=1");
		SucursalDao sudao = new SucursalDao();
		Sucursal unaSucursal= sudao.traer(1);
		System.out.println(unaSucursal.toString());
		
		System.out.println("Traemos Cliente id=1");
		PersonaDao perdao = new PersonaDao();
		Cliente unCliente= perdao.traerCliente(2);
		System.out.println(unCliente.toString());
		
		System.out.println("Traemos Empleado id=2");
		Empleado unEmpleado= perdao.traerEmpleado(1);
		System.out.println(unEmpleado.toString());
		
		System.out.println("Traemos Estado id=1");
		EstadoDao estdao = new EstadoDao();
		Estado unEstado= estdao.traer(1);
		System.out.println(unEstado.toString());
		/*
		TurnoDao tdao = new TurnoDao();
		Turno t = new Turno(LocalDateTime.now(), unServicio, unaSucursal, unCliente, unEmpleado, unEstado);
		System.out.println("DALE");
		tdao.agregar(t);
		*/
		System.out.println("Traemos el turno");
		TurnoDao turndao = new TurnoDao();
		System.out.println(turndao.traer(1));
		
	}

}
