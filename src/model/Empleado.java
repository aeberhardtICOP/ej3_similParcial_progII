package model;

import java.util.Date;

public class Empleado extends Integrante{
	private boolean esAfiliado;
	private Date fechaIngreso;
	private Funciones funcion;
	
	public Empleado(Long id, int nroLegajo, int añoNacimiento, String nombre, String apellido, int dni, int edad,
			Organismo organismo, boolean esAfiliado, Date fechaIngreso, Funciones funcion) {
		super(id, nroLegajo, añoNacimiento, nombre, apellido, dni, edad, organismo);
		this.esAfiliado = esAfiliado;
		this.fechaIngreso = fechaIngreso;
		this.funcion = funcion;
	}

	public Empleado() {
		super();
	}

	public boolean isEsAfiliado() {
		return esAfiliado;
	}

	public void setEsAfiliado(boolean esAfiliado) {
		this.esAfiliado = esAfiliado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Funciones getFuncion() {
		return funcion;
	}

	public void setFuncion(Funciones funcion) {
		this.funcion = funcion;
	}
	
	
}
