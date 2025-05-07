package dao;

import datos.Estado;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;


public class EstadoDao {
	
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
	
	public int agregar(Estado objeto) {
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
	
	public void actualizar(Estado objeto) {
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
	
	public void eliminar(Estado objeto) {
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
	
	public Estado traer(int idEstado) {
		Estado objeto = null;
		try {
			iniciaOperacion();
			objeto = (Estado) session.get(Estado.class, idEstado);
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
	public List<Estado> traer() {
		List<Estado> lista = new ArrayList<Estado>();
		try {
			iniciaOperacion();
			Query<Estado> query = session.createQuery("FROM Estado", Estado.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public Estado traerPorDescripcion(String descripcion) {
	    Estado estado = null;
	    try {
	        iniciaOperacion();
	        String hql = "FROM Estado e WHERE e.descripcion = :descripcion";
	        Query<Estado> query = session.createQuery(hql, Estado.class);
	        query.setParameter("descripcion", descripcion);
	        estado = query.uniqueResult();
	    } finally {
	        session.close();
	    }
	    return estado;
	}
}