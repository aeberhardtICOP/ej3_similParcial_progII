package model;

import java.util.Date;

public class Funcionario extends Integrante {
	private boolean acuerdoLegislatico;
	private Cargos cargo;
	private Date fechaPosecion;
	
	public Funcionario(Long id, int nroLegajo, int añoNacimiento, String nombre, String apellido, int dni, int edad,
			Organismo organismo, boolean acuerdoLegislatico, Cargos cargo, Date fechaPosecion) {
		super(id, nroLegajo, añoNacimiento, nombre, apellido, dni, edad, organismo);
		this.acuerdoLegislatico = acuerdoLegislatico;
		this.cargo = cargo;
		this.fechaPosecion = fechaPosecion;
	}
	
	

	public Funcionario() {
		super();
	}



	public boolean isAcuerdoLegislatico() {
		return acuerdoLegislatico;
	}

	public void setAcuerdoLegislatico(boolean acuerdoLegislatico) {
		this.acuerdoLegislatico = acuerdoLegislatico;
	}

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	public Date getFechaPosecion() {
		return fechaPosecion;
	}

	public void setFechaPosecion(Date fechaPosecion) {
		this.fechaPosecion = fechaPosecion;
	}
	
	
}
