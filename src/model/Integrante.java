package model;

public abstract class Integrante {
	private Long id;
	private int nroLegajo;
	private int añoNacimiento;
	private String nombre;
	private String apellido;
	private int dni;
	private int edad;
	private Organismo organismo;
	
	public Integrante(Long id, int nroLegajo, int añoNacimiento, String nombre, String apellido, int dni, int edad, Organismo organismo) {
		this.id = id;
		this.nroLegajo = nroLegajo;
		this.añoNacimiento = añoNacimiento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad = edad;
		this.organismo=organismo;
	}

	public Integrante() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public int getAñoNacimiento() {
		return añoNacimiento;
	}

	public void setAñoNacimiento(int añoNacimiento) {
		this.añoNacimiento = añoNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Organismo getOrganismo() {
		return organismo;
	}

	public void setOrganismo(Organismo organismo) {
		this.organismo = organismo;
	}
	
	
	
}
