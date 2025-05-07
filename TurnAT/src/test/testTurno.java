package test;

import java.time.LocalDateTime;

import dao.TurnoDao;
import datos.Cliente;
import datos.Empleado;
import datos.Estado;
import datos.Servicio;
import datos.Sucursal;
import datos.Turno;
import negocio.*;


public class testTurno {
    public static void main(String[] args) {
       TurnoDao daoTurno = new TurnoDao();
        /* PersonaABM PersonaABM = new PersonaABM();
        EstadoABM EstadoABM = new EstadoABM();
        SucursalABM SucursalABM = new SucursalABM();
        ServicioABM  ServicioABM = new  ServicioABM();
        
        Cliente cliente1 = PersonaABM.traerCliente(2);
        
        Empleado empleado1 = PersonaABM.traerEmpleado(1);
        Estado estado1 = EstadoABM.traer(1);
        Sucursal sucursal1 = SucursalABM.traer(1);
        Servicio servicio1 = ServicioABM.traer(1);
        LocalDateTime fechaYHora = LocalDateTime.now();
        //(LocalDateTime fechaYHora, Servicio srv, Sucursal suc, Cliente cli, Empleado emp, Estado est)
        
        Turno turno = new Turno(fechaYHora, servicio1, sucursal1, cliente1, empleado1, estado1);
        daoTurno.agregar(turno);*/
        
        //System.out.println(daoTurno.traer(1));
       
        Turno t = daoTurno.traer(1);
        
       System.out.println( t.getSuc().getDireccion());
        
        
        
        
        
        
        
    }    
}