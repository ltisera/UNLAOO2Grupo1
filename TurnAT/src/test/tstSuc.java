package test;
import dao.SucursalDao;
import datos.Sucursal;
import datos.Servicio;
import datos.Turno;
public class tstSuc {

	
	public static void main(String[] args) {
		Sucursal suc = new Sucursal("Rocio", "Una", 23344);
		SucursalDao sdao = new SucursalDao();
		sdao.agregar(suc);
		System.out.println("POR QUE NO ME AUTO COMPLETA ");
		// TODO Auto-generated method stub
		

	}

}
