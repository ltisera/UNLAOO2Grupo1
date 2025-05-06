package test;

import negocio.ServicioABM;

public class testServicio {
	public static void main(String[] args) {
        ServicioABM abm = new ServicioABM();
        
        // Intenta agregar servicios (aunque ya existan)
        abm.agregar("Corte de pelo", "Corte básico"); // Si ya existe, mostrará advertencia
        abm.agregar("Coloración", "Tinte profesional"); // Si no existe, lo agregará
    }
}