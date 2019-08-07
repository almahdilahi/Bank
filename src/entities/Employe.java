package entities;

import java.io.InputStream;

public class Employe {
	private int idE;
	private int tel;
	private int idPoste;
	private int idAgence;
	private String matricule;
	private String nom;
	private String password;
	private InputStream photo ;
	public static String paramsCreateur;
	public Employe(int idE, int tel, int idPoste,int idAgence, String matricule, String nom, String password,InputStream photo) {
		
		this.idE = idE;
		this.tel = tel;
		this.idPoste = idPoste;
		this.idAgence=idAgence;
		this.matricule = matricule;
		this.nom = nom;
		this.password = password;
		this.photo=photo;
	}
	
	public Employe() {
		
	}

	public int getIdE() {
		return idE;
	}

	public void setIdE(int idE) {
		this.idE = idE;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getIdPoste() {
		return idPoste;
	}

	public void setIdPoste(int idPoste) {
		this.idPoste = idPoste;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream img) {
		this.photo = img;
	}

	public int getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}
	
	
	

}
