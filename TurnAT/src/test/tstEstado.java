package test;
import dao.EstadoDao;
import datos.Estado;

public class tstEstado {

	public static void main(String[] args) {
		Estado est = new Estado("disponible");
		EstadoDao edao = new EstadoDao();
		edao.agregar(est);
		System.out.println("LISTO");
		// TODO Auto-generated method stub

	}

}
