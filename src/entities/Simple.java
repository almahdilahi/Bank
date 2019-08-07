package entities;

import java.time.LocalDate;

public class Simple extends Compte {
	private static int fraisOuv ;
	private static float tauxRem ;
	static {fraisOuv = 40000; tauxRem = 0.05f;}
	public Simple(int idCompte, String numero, int solde, int idClient, int idCreateur, int idConseille, int idAgence,
			LocalDate dateouverture, String etat,float tauxRem, int fraisOuv) {
		super(idCompte, numero, solde, idClient, idCreateur, idConseille, idAgence, dateouverture,etat);
		
	}

	public Simple() {
		
	}

	public static int getFraisOuv() {
		return fraisOuv;
	}

	public static void setFraisOuv(int fraisOuv) {
		Simple.fraisOuv = fraisOuv;
	}

	public static float getTauxRem() {
		return tauxRem;
	}

	public static void setTauxRem(float tauxRem) {
		Simple.tauxRem = tauxRem;
	}
	

}
