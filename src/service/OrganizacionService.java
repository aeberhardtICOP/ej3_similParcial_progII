package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import model.Organismo;
import persistence.OrganizacionPersistence;

public class OrganizacionService {
	private OrganizacionPersistence orgpers;
	private HashMap<Long, Organismo>organismos;
	
	public OrganizacionService() {
		this.orgpers=new OrganizacionPersistence();
		this.organismos=new HashMap<Long, Organismo>();
	}
	
	public void crearOrganismo(String nombre, String fechaCreacion, String domicilio) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Organismo org = new Organismo();
		org.setNombre(nombre);
		try {
			org.setFechaCreaciom(formato.parse(fechaCreacion));
		} catch (ParseException e) {
			e.printStackTrace();
			org.setFechaCreaciom(null);
		}
		org.setDomicilio(domicilio);
		org.setId(orgpers.ultimoId()+1);
		orgpers.crearOrganismo(org);
		this.organismos.put(orgpers.ultimoId()+1, org);
	}
	
	public void organismosAMemoria() {
		this.organismos=orgpers.traerOrganismos();
		System.out.println(organismos.get(1L).getNombre());
	}
	
	public HashMap<Long, Organismo> obtenerOrganismos() {
		return this.organismos;
	}
}
