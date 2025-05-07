package dao;

import datos.Turno;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;



import java.util.List;

public class TurnoDao {
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

    public int agregar(Turno objeto) {
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


    public void actualizar(Turno objeto) {
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


    public void eliminar(Turno objeto) {
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


    public Turno traer(int idTurno) {

        Turno objeto = null;
        try {
            iniciaOperacion();
            String hql = "FROM Turno t " +
                    "JOIN FETCH t.srv " +
                    "JOIN FETCH t.emp " +
                    "JOIN FETCH t.suc " +
                    "JOIN FETCH t.cli " +
                    "JOIN FETCH t.est " +
                    "WHERE t.idTurno = :idTurno";
            objeto = session.createQuery(hql, Turno.class)
                             .setParameter("idTurno", idTurno)
                             .uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }
 
    public List<Turno> traer() {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            Query<Turno> query = session.createQuery("from Turno", Turno.class);

            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

    
    
    public List<Turno> traerTurnosCliente(int idCliente) {

        List<Turno> lista = null;
        try {
            iniciaOperacion();
            String hql = "FROM Turno t "
                    + "JOIN FETCH t.srv "
                    + "JOIN FETCH t.emp "
                    + "JOIN FETCH t.suc "
                    + "JOIN FETCH t.cli "
                    + "JOIN FETCH t.est "
                    + "WHERE t.cli.idPersona = :idCliente";
            Query<Turno> query =session.createQuery(hql, Turno.class);
            query.setParameter("idCliente", idCliente);
            lista = query.getResultList(); 
        } finally {
            session.close();
        }
        return lista;
    }

    
    
    // Util para el caso de uso Solicitar turno (ID:006)
    public List<Turno> traerPorServicioSucursalEstado(int idServicio, int idEstado, int idSucursal) {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            String hql ="FROM Turno t " +
            			"JOIN FETCH t.srv " +
            			"JOIN FETCH t.emp " +
            			"JOIN FETCH t.suc " +
            			"JOIN FETCH t.cli " +
            			"JOIN FETCH t.est " +
                        "where t.suc.idSucursal = :idSucursal and t.srv.idServicio = :idServicio and t.est.idEstado =: idEstado";
            Query<Turno> query = session.createQuery(hql, Turno.class);
            query.setParameter("idSucursal", idSucursal);
            query.setParameter("idServicio", idServicio);
            query.setParameter("idEstado", idEstado);
           
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    

}