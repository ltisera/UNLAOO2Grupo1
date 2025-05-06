package test;

import org.hibernate.Session;
import dao.HibernateUtil;

public class testConexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

				System.out.println("Iniciando Hibernate...");
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.close();
				System.out.println("ok");
		
		

	}

}
