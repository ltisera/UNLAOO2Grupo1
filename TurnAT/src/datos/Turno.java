package datos;

import java.time.LocalDate;

import datos.Sucursal;
import datos.Cliente;
import datos.Empleado;
import datos.Estado;
import datos.Servicio;

public class Turno {
	private int idTurno;
	private LocalDate fechaYHora;
	private Servicio srv;
	private Sucursal suc;
	private Cliente cli;
	private Empleado emp;
	private Estado est;
	public int getIdTurno() {
		return idTurno;
	}
	protected void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public LocalDate getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(LocalDate fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	public Servicio getSrv() {
		return srv;
	}
	public void setSrv(Servicio srv) {
		this.srv = srv;
	}
	public Sucursal getSuc() {
		return suc;
	}
	public void setSuc(Sucursal suc) {
		this.suc = suc;
	}
	public Cliente getCli() {
		return cli;
	}
	public void setCli(Cliente cli) {
		this.cli = cli;
	}
	public Empleado getEmp() {
		return emp;
	}
	public void setEmp(Empleado emp) {
		this.emp = emp;
	}
	public Estado getEst() {
		return est;
	}
	public void setEst(Estado est) {
		this.est = est;
	}
	
		
}
