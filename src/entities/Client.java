package entities;

import java.io.InputStream;
import java.time.LocalDate;

import javafx.scene.image.Image;

public class Client {
	
	private int idC;
	private int INE;
	private int tel;
	private LocalDate datenaiss;
	private String nomcomplet;
	private String profession;
	private String adresse;
	private String email;
	private InputStream photo ;
	//private int idEmp;
	public Client(int idC, int iNE, int tel, LocalDate datenaiss, String nomcomplet, String profession, String adresse,
			String email,InputStream photo) {
		
		this.idC = idC;
		INE = iNE;
		this.tel = tel;
		this.datenaiss = datenaiss;
		this.nomcomplet = nomcomplet;
		this.profession = profession;
		this.adresse = adresse;
		this.email = email;
		this.photo=photo;
	}
	
	public Client() {
		
	}

	
/*	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}*/

	public int getIdC() {
		return idC;
	}

	public void setIdC(int idC) {
		this.idC = idC;
	}

	public int getINE() {
		return INE;
	}

	public void setINE(int iNE) {
		INE = iNE;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public LocalDate getDatenaiss() {
		return datenaiss;
	}

	public void setDatenaiss(LocalDate datenaiss) {
		this.datenaiss = datenaiss;
	}

	public String getNomcomplet() {
		return nomcomplet;
	}

	public void setNomcomplet(String nomcomplet) {
		this.nomcomplet = nomcomplet;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream img) {
		this.photo = img;
	}
	
	
		
	

}
