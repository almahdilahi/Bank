package entities;

import java.sql.Date;
import java.time.LocalDate;

public abstract class  Compte  {
	
	private int idCompte;
	private String numero;
	private int solde;
	private int idClient;
	private int idCreateur;
	private int idConseille;
	private int idAgence;
	private LocalDate dateouverture;
	private Date dtouv;
	private String etat;
	private String type;
	private int agio;
	private int fraisouv;
	private float tauxren;
	private static int nb=0;
	public Compte(int idCompte, String numero, int solde, int idClient, int idCreateur, int idConseille, int idAgence,
			LocalDate dateouverture,String etat) {
		
		this.idCompte = idCompte;
		nb++;
		this.numero = "CP"+(nb<10000?"0":"")+ (nb<1000?"0":"") + (nb<100?"0":"") + (nb<10?"0":"") + nb;
		this.solde = solde;
		this.idClient = idClient;
		this.idCreateur = idCreateur;
		this.idConseille = idConseille;
		this.idAgence = idAgence;
		this.dateouverture = dateouverture;
	}
	
	public Compte() {
		
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdCreateur() {
		return idCreateur;
	}

	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}

	public int getIdConseille() {
		return idConseille;
	}

	public void setIdConseille(int idConseille) {
		this.idConseille = idConseille;
	}

	public int getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}

	public LocalDate getDateouverture() {
		return dateouverture;
	}

	public void setDateouverture(LocalDate dateouverture) {
		this.dateouverture = dateouverture;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getAgio() {
		return agio;
	}

	public Date getDtouv() {
		return dtouv;
	}

	public void setDtouv(Date dtouv) {
		this.dtouv = dtouv;
	}

	public void setAgio(int agio) {
		this.agio = agio;
	}

	public int getFraisouv() {
		return fraisouv;
	}

	public void setFraisouv(int fraisouv) {
		this.fraisouv = fraisouv;
	}

	public float getTauxren() {
		return tauxren;
	}

	public void setTauxren(float tauxren) {
		this.tauxren = tauxren;
	}
	
	
}
