package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import model.Cargos;
import model.Empleado;
import model.Funcionario;
import model.Funciones;
import model.Integrante;
import model.Organismo;
import persistence.IntegrantePersistence;

public class IntegranteService {
	private IntegrantePersistence intper;
	private HashMap<Long, Integrante>integrantes;
	
	
	public IntegranteService() {
		this.intper=new IntegrantePersistence();
		this.integrantes=new HashMap<Long, Integrante>();
	}
	public void integrantesAMemoria() {
		this.integrantes=intper.traerIntegrantes();
	}
	
	public void crearIntegrante(String nroLegajo, String tipo, String circunscripcion, String nombre, String apellido, String dni, 
			String edad, String añoNacimiento, Organismo organizacion, String fechaPosecion, boolean acuerdoLegislativo, String cargo,
			String fechaIngreso, boolean afiliado, String funcion) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if(tipo.equals("Empleado")) {
			Empleado emp = new Empleado();
			emp.setNroLegajo(Integer.parseInt(nroLegajo));
			emp.setNombre(nombre);
			emp.setApellido(apellido);
			emp.setDni(Integer.parseInt(dni));
			emp.setEdad(Integer.parseInt(edad));
			emp.setAñoNacimiento(Integer.parseInt(añoNacimiento));
			emp.setOrganismo(organizacion);
			
			try {
				emp.setFechaIngreso(formato.parse(fechaIngreso));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			emp.setEsAfiliado(afiliado);
			emp.setFuncion(stringAFunciones(funcion));
			intper.crearIntegrante(emp, circunscripcion);
			this.integrantes.put((long) intper.ultimoId(), emp);
		}else if(tipo.equals("Funcionario")) {
			Funcionario fun = new Funcionario();
			fun.setNroLegajo(Integer.parseInt(nroLegajo));
			fun.setNombre(nombre);
			fun.setApellido(apellido);
			fun.setDni(Integer.parseInt(dni));
			fun.setEdad(Integer.parseInt(edad));
			fun.setAñoNacimiento(Integer.parseInt(añoNacimiento));
			fun.setOrganismo(organizacion);
			
			try {
				fun.setFechaPosecion(formato.parse(fechaPosecion));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fun.setAcuerdoLegislatico(acuerdoLegislativo);
			fun.setCargo(stringACargos(cargo));
			intper.crearIntegrante(fun, circunscripcion);
			this.integrantes.put((long) intper.ultimoId(), fun);
		}
	}
	
	public HashMap<Long, Integrante> getIntegrantes(){
		return this.integrantes;
	}
	
	 public Cargos stringACargos(String cargo) {
	    	if(cargo.equals("INICIAL")) {
	    		return Cargos.INICIAL;
	    	}else if(cargo.equals("SUPERIOR")) {
	    		return Cargos.SUPERIOR;
	    	}
	    	return null; 
	    }
	    public Funciones stringAFunciones(String funcion) {
	    	if(funcion.equals("ADMINISTRATIVO")) {
	    		return Funciones.ADMINISTRATIVO;
	    	}else if(funcion.equals("ORDENANZA")) {
	    		return Funciones.ORDENANZA;
	    	}else if(funcion.equals("ESPECIALIZADO")){
	    		return Funciones.ESPECIALIZADO;
	    	}else {
	    		return null; 
	    	}
	    }
}
