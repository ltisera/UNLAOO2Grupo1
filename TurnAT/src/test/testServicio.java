package test;

import negocio.ServicioABM;

public class testServicio {
    public static void main(String[] args) {
        ServicioABM abm = new ServicioABM();

        // Agregar un servicio
        int idCorte = abm.agregar("Corte de pelo", "Corte básico con tijera");
        System.out.println("Servicio creado con ID: " + idCorte);

        // Listar todos
        System.out.println("Todos los servicios:");
        abm.traerTodos().forEach(System.out::println);
    }
}