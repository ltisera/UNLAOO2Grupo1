package test;

import excepciones.TurnosException;
import negocio.ServicioABM;

public class testServicio {
	public static void main(String[] args) {
        ServicioABM abm = new ServicioABM();
        
        // Intenta agregar servicios (aunque ya existan)
       try {
    	   abm.agregar("Corte de pelo", "Corte básico"); // Si ya existe, mostrará advertencia
       
        
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }


       try {
    	   abm.agregar("Coloración", "Tinte profesional"); // Si no existe, lo agregará
       
        }catch(TurnosException e) {
        	System.out.println(e.getMessage());
        }
	
	}
}