package test;
import dao.ServicioDao;
import datos.Servicio;
public class tstSrvc {

	
	public static void main(String[] args) {
		Servicio srv = new Servicio("Oftalmologia", "Lun y vier");
		ServicioDao sdao = new ServicioDao();
		sdao.agregar(srv);
		System.out.println("POR QUE NO ME AUTO COMPLETA ");
		// TODO Auto-generated method stub

	}

}
