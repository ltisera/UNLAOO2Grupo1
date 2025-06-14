package com.turnat.TurnAT.dto;

public class TurnoDTO {
	
	 private int idServicio;
	    private int idSucursal;  // Lo usamos para lógica pero no va directo en Turno
	    private int anio;
	    private int mes;
	    private int dia;
	    private String hora; 
	    private int idCliente;
	    
	    public TurnoDTO() {}
	    public TurnoDTO(int idServicio, int idSucursal, int anio, int mes, int dia, String hora, int idCliente) {
			super();
			this.idServicio = idServicio;
			this.idSucursal = idSucursal;
			this.anio = anio;
			this.mes = mes;
			this.dia = dia;
			this.hora = hora;
			this.idCliente = idCliente;
		}
		
	    public int getIdServicio() {
			return idServicio;
		}
		public void setIdServicio(int idServicio) {
			this.idServicio = idServicio;
		}
		public int getIdSucursal() {
			return idSucursal;
		}
		public void setIdSucursal(int idSucursal) {
			this.idSucursal = idSucursal;
		}
		public int getAnio() {
			return anio;
		}
		public void setAnio(int anio) {
			this.anio = anio;
		}
		public int getMes() {
			return mes;
		}
		public void setMes(int mes) {
			this.mes = mes;
		}
		public int getDia() {
			return dia;
		}
		public void setDia(int dia) {
			this.dia = dia;
		}
		public String getHora() {
			return hora;
		}
		public void setHora(String hora) {
			this.hora = hora;
		}
		public int getIdCliente() {
			return idCliente;
		}
		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}

	    


}
