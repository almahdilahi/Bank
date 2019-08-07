package entities;

import java.time.LocalDate;

public class Courant extends Compte {

	private static int agio;
	private static float tauxRem ;
	static {tauxRem = 0.07f;}
	public Courant(int idCompte, String numero, int solde, int idClient, int idCreateur, int idConseille, int idAgence,
			LocalDate dateouverture,String etat, float tauxRem, int agio) {
		super(idCompte, numero, solde, idClient, idCreateur, idConseille, idAgence, dateouverture,etat);
		if(solde >= 75000 && solde <=150000){
		this.agio = 3000;
		}else if(solde <=300000){
			this.agio = 5000;
		}else if(solde <=600000){
			this.agio = 7000;
		}else if(solde <=900000){
			this.agio = 10000;
		}else{
			this.agio = 15000;
		}
	}
	public Courant() {
		
	}
	public int getAgio() {
		return this.agio;
	}
	public void setAgio(int agio) {
		Courant.agio = agio;
	}
	public static float getTauxRem() {
		return tauxRem;
	}
	public static void setTauxRem(float tauxRem) {
		Courant.tauxRem = tauxRem;
	}
	
	

}
