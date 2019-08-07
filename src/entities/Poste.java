package entities;

public class Poste {

	private int idP;
	private String libelleP;
	public Poste(int idP, String libelleP) {
		this.idP = idP;
		this.libelleP = libelleP;
	}
	
	public Poste() {
		
	}

	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	public String getLibelleP() {
		return libelleP;
	}

	public void setLibelleP(String libelleP) {
		this.libelleP = libelleP;
	}
	
	
	
}
