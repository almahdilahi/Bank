package entities;

public class Agence {

	private int idA;
	private String nom;
	private String adresse;
	public Agence(int idA, String nom, String adresse) {
		
		this.idA = idA;
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public Agence() {
		
	}

	public int getIdA() {
		return idA;
	}

	public void setIdA(int idA) {
		this.idA = idA;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
	
}
