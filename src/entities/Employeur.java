package entities;

import java.io.InputStream;

public class Employeur extends Employe {
	private int idEmp;
	private String nomEmp;
	private String adr;
	private String domaine;

	public Employeur(int idE, int tel, int idPoste, int idAgence, String matricule, String nom, String password,
			InputStream photo) {
	
	}

	public Employeur() {
	
	}

	
	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public String getNomEmp() {
		return nomEmp;
	}

	public void setNomEmp(String nomEmp) {
		this.nomEmp = nomEmp;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
	

}
