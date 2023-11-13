package model;

import java.util.Date;
import java.util.HashMap;

public class Organismo {
	private Long id;
	private Date fechaCreaciom;
	private String domicilio;
	private String nombre;
	private HashMap<Long, Integrante>integrantesRafaela;
	private HashMap<Long, Integrante>integrantesReconquista;
	private HashMap<Long, Integrante>integrantesSantaFe;
	
	
	
	public Organismo() {
	}
	public Organismo(Long id, Date fechaCreaciom, String domicilio, String nombre) {
		this.id = id;
		this.fechaCreaciom = fechaCreaciom;
		this.domicilio = domicilio;
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaCreaciom() {
		return fechaCreaciom;
	}
	public void setFechaCreaciom(Date fechaCreaciom) {
		this.fechaCreaciom = fechaCreaciom;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public HashMap<Long, Integrante> getIntegrantesRafaela() {
		return integrantesRafaela;
	}
	public void setIntegrantesRafaela(HashMap<Long, Integrante> integrantesRafaela) {
		this.integrantesRafaela = integrantesRafaela;
	}
	public HashMap<Long, Integrante> getIntegrantesReconquista() {
		return integrantesReconquista;
	}
	public void setIntegrantesReconquista(HashMap<Long, Integrante> integrantesReconquista) {
		this.integrantesReconquista = integrantesReconquista;
	}
	public HashMap<Long, Integrante> getIntegrantesSantaFe() {
		return integrantesSantaFe;
	}
	public void setIntegrantesSantaFe(HashMap<Long, Integrante> integrantesSantaFe) {
		this.integrantesSantaFe = integrantesSantaFe;
	}
	
	
	
}
