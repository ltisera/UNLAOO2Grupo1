package dao;

import datos.Turno;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    
    public void iniciarSesion() {
        iniciaOperacion();
    }
    
    public List<Turno> traerTurnosPorEmpleado(int idEmpleado) {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            String hql = "FROM Turno t " +
                         "JOIN FETCH t.srv " +
                         "JOIN FETCH t.emp " +
                         "JOIN FETCH t.suc " +
                         "JOIN FETCH t.cli " +
                         "JOIN FETCH t.est " +
                         "WHERE t.emp.idPersona = :idEmpleado";
            lista = session.createQuery(hql, Turno.class)
                          .setParameter("idEmpleado", idEmpleado)
                          .getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    
    
    //1.5 Minimo cuatro consultas por intervalo de fechas
    
    public List<Turno> traerPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            String hql = "FROM Turno t " +
                         "JOIN FETCH t.srv " +
                         "JOIN FETCH t.emp " +
                         "JOIN FETCH t.suc " +
                         "JOIN FETCH t.cli " +
                         "JOIN FETCH t.est " +
                         "WHERE t.fechaYHora BETWEEN :desde AND :hasta " +
                         "ORDER BY t.fechaYHora";
            lista = session.createQuery(hql, Turno.class)
                          .setParameter("desde", desde)
                          .setParameter("hasta", hasta)
                          .getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

    public List<Turno> traerPorDia(LocalDate dia) {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            LocalDateTime inicioDia = dia.atStartOfDay();
            LocalDateTime finDia = dia.plusDays(1).atStartOfDay();
            
            String hql = "FROM Turno t " +
                         "JOIN FETCH t.srv " +
                         "JOIN FETCH t.emp " +
                         "JOIN FETCH t.suc " +
                         "JOIN FETCH t.cli " +
                         "JOIN FETCH t.est " +
                         "WHERE t.fechaYHora >= :inicioDia " +
                         "AND t.fechaYHora < :finDia";
            lista = session.createQuery(hql, Turno.class)
                          .setParameter("inicioDia", inicioDia)
                          .setParameter("finDia", finDia)
                          .getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Turno> traerPorMes(int anio, int mes) {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            LocalDateTime inicioMes = LocalDateTime.of(anio, mes, 1, 0, 0);
            LocalDateTime finMes = inicioMes.plusMonths(1);
            
            String hql = "FROM Turno t " +
                         "JOIN FETCH t.srv " +
                         "JOIN FETCH t.emp " +
                         "JOIN FETCH t.suc " +
                         "JOIN FETCH t.cli " +
                         "JOIN FETCH t.est " +
                         "WHERE t.fechaYHora >= :inicioMes " +
                         "AND t.fechaYHora < :finMes";
            lista = session.createQuery(hql, Turno.class)
                          .setParameter("inicioMes", inicioMes)
                          .setParameter("finMes", finMes)
                          .getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Turno> traerPorRangoFechasYEstado(LocalDateTime desde, LocalDateTime hasta, int idEstado) {
        List<Turno> lista = null;
        try {
            iniciaOperacion();
            String hql = "FROM Turno t " +
                         "JOIN FETCH t.srv " +
                         "JOIN FETCH t.emp " +
                         "JOIN FETCH t.suc " +
                         "JOIN FETCH t.cli " +
                         "JOIN FETCH t.est " +
                         "WHERE t.fechaYHora BETWEEN :desde AND :hasta " +
                         "AND t.est.idEstado = :idEstado";
            lista = session.createQuery(hql, Turno.class)
                          .setParameter("desde", desde)
                          .setParameter("hasta", hasta)
                          .setParameter("idEstado", idEstado)
                          .getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}