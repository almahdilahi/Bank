package entities;

import java.time.LocalDate;

public class Operation {

	private int idO;
	private int montant;
	private int idCompte;
	private int idCaissier;
	private int idAgence;
	private String type;
	private LocalDate dateop;
	public static String paramsCaissier;
	public Operation(int idO, int montant, int idCompte, int idCaissier, int idAgence, String type, LocalDate dateop) {
		
		this.idO = idO;
		this.montant = montant;
		this.idCompte = idCompte;
		this.idCaissier = idCaissier;
		this.idAgence = idAgence;
		this.type = type;
		this.dateop = dateop;
	}
	
	public Operation() {
		
	}

	public int getIdO() {
		return idO;
	}

	public void setIdO(int idO) {
		this.idO = idO;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public int getIdCaissier() {
		return idCaissier;
	}

	public void setIdCaissier(int idCaissier) {
		this.idCaissier = idCaissier;
	}

	public int getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getDateop() {
		return dateop;
	}

	public void setDateop(LocalDate dateop) {
		this.dateop = dateop;
	}
	
	
}
