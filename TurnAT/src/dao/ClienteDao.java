package dao;

import datos.Cliente;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ClienteDao {
    private static Session session;
    private Transaction tx;

    // Métodos básicos de operación
    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Error en ClienteDao", he);
    }

    // --- CRUD ---
    public int agregar(Cliente cliente) {
        int id = 0;
        try {
            iniciaOperacion();
            id = (int) session.save(cliente);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Cliente cliente) {
        try {
            iniciaOperacion();
            session.update(cliente);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public void eliminar(Cliente cliente) {
        try {
            iniciaOperacion();
            session.delete(cliente);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    // --- Consultas específicas ---
    public Cliente traer(int idCliente) {
        Cliente cliente = null;
        try {
            iniciaOperacion();
            cliente = session.get(Cliente.class, idCliente);
        } finally {
            session.close();
        }
        return cliente;
    }

    public Cliente traerPorDni(int dni) {
        Cliente cliente = null;
        try {
            iniciaOperacion();
            Query<Cliente> query = session.createQuery(
                "FROM Cliente c WHERE c.dni = :dni", Cliente.class);
            query.setParameter("dni", dni);
            cliente = query.uniqueResult();
        } finally {
            session.close();
        }
        return cliente;
    }

    public List<Cliente> traerTodos() {
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
}