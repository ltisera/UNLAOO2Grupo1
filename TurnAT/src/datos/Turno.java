package datos;

import java.time.LocalDateTime;

public class Turno {
	private int idTurno;
	private LocalDateTime fechaYHora;
	private Servicio srv;
	private Sucursal suc;
	private Cliente cli;
	private Empleado emp;
	private Estado est;

	public Turno() {}
	public Turno(LocalDateTime fechaYHora, Servicio srv, Sucursal suc, Cliente cli, Empleado emp, Estado est) {
		super();
		this.fechaYHora = fechaYHora;
		this.srv = srv;
		this.suc = suc;
		this.cli = cli;
		this.emp = emp;
		this.est = est;
	}


	public int getIdTurno() {
		return idTurno;
	}
	protected void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(LocalDateTime fechaYHora) {
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
	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", fechaYHora=" + fechaYHora + ", srv=" + srv + ", suc=" + suc + ", cli="
				+ cli + ", emp=" + emp + ", est=" + est + "]";
	}


}
