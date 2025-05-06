package dao;

import datos.Turno;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.time.LocalDateTime;
import java.util.List;

public class TurnoDao {
    private static Session session;
    private Transaction tx;

    // Métodos básicos de operación
    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Error en TurnoDao", he);
    }

    // --- CRUD ---
    public int agregar(Turno turno) {
        int id = 0;
        try {
            iniciaOperacion();
            id = (int) session.save(turno);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Turno turno) {
        try {
            iniciaOperacion();
            session.update(turno);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public void eliminar(Turno turno) {
        try {
            iniciaOperacion();
            session.delete(turno);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public Turno traer(int idTurno) {
        Turno turno = null;
        try {
            iniciaOperacion();
            turno = session.get(Turno.class, idTurno);
        } finally {
            session.close();
        }
        return turno;
    }

    // --- Métodos específicos para Turno ---
    public List<Turno> traerTodos() {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            Query<Turno> query = session.createQuery("FROM Turno", Turno.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

    public List<Turno> traerPorEmpleado(int idEmpleado) {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            Query<Turno> query = session.createQuery(
                "FROM Turno t WHERE t.empleado.idPersona = :idEmpleado", Turno.class);
            query.setParameter("idEmpleado", idEmpleado);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

    public boolean existeSolapamiento(LocalDateTime fecha, int idEmpleado) {
        try {
            iniciaOperacion();
            Query<Long> query = session.createQuery(
                "SELECT COUNT(t) FROM Turno t WHERE t.empleado.idPersona = :idEmpleado " +
                "AND t.fechaYHora = :fecha", Long.class);
            query.setParameter("idEmpleado", idEmpleado);
            query.setParameter("fecha", fecha);
            return query.uniqueResult() > 0;
        } finally {
            session.close();
        }
    }
}