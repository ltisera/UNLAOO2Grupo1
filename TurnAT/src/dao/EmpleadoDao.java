package dao;

import datos.Empleado;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EmpleadoDao {
    private static Session session;
    private Transaction tx;

    // Métodos básicos de operación
    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Error en EmpleadoDao", he);
    }

    // --- CRUD ---
    public int agregar(Empleado empleado) {
        int id = 0;
        try {
            iniciaOperacion();
            id = (int) session.save(empleado);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Empleado empleado) {
        try {
            iniciaOperacion();
            session.update(empleado);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public void eliminar(Empleado empleado) {
        try {
            iniciaOperacion();
            session.delete(empleado);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    // --- Consultas específicas ---
    public Empleado traer(int idEmpleado) {
        Empleado empleado = null;
        try {
            iniciaOperacion();
            empleado = session.get(Empleado.class, idEmpleado);
        } finally {
            session.close();
        }
        return empleado;
    }

    public Empleado traerPorLegajo(int legajo) {
        Empleado empleado = null;
        try {
            iniciaOperacion();
            Query<Empleado> query = session.createQuery(
                "FROM Empleado e WHERE e.legajo = :legajo", Empleado.class);
            query.setParameter("legajo", legajo);
            empleado = query.uniqueResult();
        } finally {
            session.close();
        }
        return empleado;
    }

    public List<Empleado> traerTodos() {
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
}