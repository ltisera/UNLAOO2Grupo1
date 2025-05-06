package dao;
import datos.Cliente;
import datos.Empleado;
import datos.Persona;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class PersonaDao {
	
		private static Session session;
		private Transaction tx;
		private void iniciaOperacion() throws HibernateException {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
		}
		
		private void manejaExcepcion(HibernateException he) throws HibernateException {
			tx.rollback();
			throw new HibernateException("ERROR en la capa de acceso a datos", he);
		}
		
		public int agregar(Persona objeto) {
			int id = 0;
			try {
				iniciaOperacion();
				id = Integer.parseInt(session.save(objeto).toString());
				tx.commit();
			} catch (HibernateException he) {
				manejaExcepcion(he);
			} finally {
				session.close();
			}
			return id;
		}
		
		public void actualizar(Persona objeto) {
			try {
				iniciaOperacion();
				session.update(objeto);
				tx.commit();
			} catch (HibernateException he) {
				manejaExcepcion(he);
			} finally {
				session.close();
			}
		}
		
		public void eliminar(Persona objeto) {
			try {
				iniciaOperacion();
				session.delete(objeto);
				tx.commit();
			} catch (HibernateException he) {
				manejaExcepcion(he);
			} finally {
				session.close();
			}
		}
		
		public Persona traer(int idPersona) {
			Persona objeto = null;
			try {
				iniciaOperacion();
				objeto = (Persona) session.get(Persona.class, idPersona);
			} finally {
				session.close();
			}
			return objeto;
		}
		
		
		public List<Persona> traer() {
			List<Persona> lista = new ArrayList<Persona>();
			try {
				iniciaOperacion();
				Query<Persona> query = session.createQuery("FROM Persona", Persona.class);
				lista = query.getResultList();
			} finally {
				session.close();
			}
			return lista;
		}
		
	//traer las subclases----
		public Cliente traerCliente(int idPersona) {
			Cliente c = null;
			try {
				iniciaOperacion();
				c = session.get(Cliente.class, idPersona);
			} finally {
				session.close();
			}
			return c;
		}
		
		public Empleado traerEmpleado(int idPersona) {
			Empleado e = null;
			try {
				iniciaOperacion();
				e = session.get(Empleado.class, idPersona);
			} finally {
				session.close();
			}
			return e;
		}
		
		public List<Cliente> traerClientes() {
			List<Cliente> lista = null;
			try {
				iniciaOperacion();
				Query<Cliente> query = session.createQuery("FROM Cliente", Cliente.class);
				lista = query.getResultList();
			} finally {
				session.close();
			}
			return lista;
		}

		public List<Empleado> traerEmpleados() {
			List<Empleado> lista = null;
			try {
				iniciaOperacion();
				Query<Empleado> query = session.createQuery("FROM Empleado", Empleado.class);
				lista = query.getResultList();
			} finally {
				session.close();
			}
			return lista;
		}
	
		//4. Consultas por algún atributo de la subclase 
		
		public Cliente traerClientePorDni(int dni) {
			Cliente objeto = null;
			try {
				iniciaOperacion();
				objeto = (Cliente)session.createQuery(" FROM Cliente c WHERE c.dni = :dni").setParameter("dni",dni).uniqueResult();
			} finally {
				session.close();
			}
			return objeto;
		}
		
		public Empleado traerEmpleadoPorCargo(String cargo) {
			Empleado objeto = null;
			try {
				iniciaOperacion();
				objeto = (Empleado)session.createQuery(" FROM Empleado e WHERE e.cargo = :cargo").setParameter("cargo",cargo).uniqueResult();
			} finally {
				session.close();
			}
			return objeto;
		}
		
}
