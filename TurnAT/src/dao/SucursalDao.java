package dao;
import datos.Sucursal;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class SucursalDao {
	
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
		
		public int agregar(Sucursal objeto) {
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
		
		public void actualizar(Sucursal objeto) {
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
		
		public void eliminar(Sucursal objeto) {
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
		
		public Sucursal traer(int idSucursal) {
			Sucursal objeto = null;
			try {
				iniciaOperacion();
				objeto = (Sucursal) session.get(Sucursal.class, idSucursal);
			} finally {
				session.close();
			}
			return objeto;
		}
		
		
		public List<Sucursal> traer() {
			List<Sucursal> lista = new ArrayList<Sucursal>();
			try {
				iniciaOperacion();
				Query<Sucursal> query = session.createQuery("FROM Sucursal", Sucursal.class);
				lista = query.getResultList();
			} finally {
				session.close();
			}
			return lista;
		}
}
