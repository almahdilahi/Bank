package entities;

import java.time.LocalDate;

public class Teranga extends Compte {
	private static int fraisOuv ;
	private static float tauxRem ;
	static {fraisOuv = 100000; tauxRem = 0.08f;}
	public Teranga(int idCompte, String numero, int solde, int idClient, int idCreateur, int idConseille, int idAgence,
			LocalDate dateouverture,String etat,float tauxRem, int fraisOuv) {
		super(idCompte, numero, solde, idClient, idCreateur, idConseille, idAgence, dateouverture,etat);
		
	}

	public Teranga() {
	
	}

	public static int getFraisOuv() {
		return fraisOuv;
	}

	public static void setFraisOuv(int fraisOuv) {
		Teranga.fraisOuv = fraisOuv;
	}

	public static float getTauxRem() {
		return tauxRem;
	}

	public static void setTauxRem(float tauxRem) {
		Teranga.tauxRem = tauxRem;
	}

	
}
