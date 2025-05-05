package dao;

import datos.Servicio;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class ServicioDao {
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
	
	public int agregar(Servicio objeto) {
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
	
	public void actualizar(Servicio objeto) {
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
	
	public void eliminar(Servicio objeto) {
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
	
	public Servicio traer(int idServicio) {
		Servicio objeto = null;
		try {
			iniciaOperacion();
			objeto = (Servicio) session.get(Servicio.class, idServicio);
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
	public List<Servicio> traer() {
		List<Servicio> lista = new ArrayList<Servicio>();
		try {
			iniciaOperacion();
			Query<Servicio> query = session.createQuery("from servicio s", Servicio.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
}
