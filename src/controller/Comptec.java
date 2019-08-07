package controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import entities.Compte;
import entities.Courant;
import entities.Simple;
import entities.Teranga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import method.IClient;
import method.ICompte;
import method.IEmploye;
import method.Outil;
import model.ClientDao;
import model.CompteDao;
import model.DB;

public class Comptec implements Initializable {
	@FXML
	private TextField numero_txt;
    @FXML
    private TextField solde_txt;

    @FXML
    private DatePicker dt_txt;

    @FXML
    private ComboBox<String> cb_cli;

    @FXML
    private ComboBox<String> cb_createur;

    @FXML
    private ComboBox<String> cb_conseiller;

    @FXML
    private ComboBox<String> cb_agence;

    @FXML
    public TextField agio_txt;

    @FXML
    private CheckBox etat1_chkb;

    @FXML
    private CheckBox etat2_chkb;

    @FXML
    private CheckBox etat3_chkb;
    @FXML
    private TextField ine_txt;

    @FXML
    private TextField fullname_txt;

    @FXML
    private TextField phone_txt;

    @FXML
    private TextField job_txt;

    @FXML
    private TextField adress_txt;

    @FXML
    private DatePicker born_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private Button btn_picture;

    @FXML
    private ImageView labimage;
    

    @FXML
    private TextField searchnomemp_txt;
    
    @FXML
    FileInputStream fIS;
    @FXML
    FileChooser fC;
    @FXML
    File selectedFile;
    @FXML
    private TextField nomemp_txt;

    @FXML
    private TextField adressemp_txt;

    @FXML
    private TextField domainemp_txt;



    
    private ICompte ic;
    private IClient icli;
    private DB db;
   	private IEmploye ie;
   	private ResultSet rs=null;
   	private ResultSet rs2=null;
   	private ResultSet rsA=null;
   	private ResultSet rsCli=null;
   	private ResultSet rsCrea=null;
   	private ResultSet rsCons=null;
   	private boolean bol;
   	private static int nb=0;
    private Image picver;  
   	public Comptec()
       {
       	db=new DB();
       }
       
      AccueilGerant acg=new AccueilGerant();
	//private String newnum; 
       
       @Override
   	public void initialize(URL arg0, ResourceBundle arg1) {
   		fillinComboBoxAgence();
   		fillinComboBoxClient();
   		fillinComboBoxEmploye();
   		numero_txt.setText("CP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte());
   		dt_txt.setValue(LocalDate.now());
   		//nb++;
   		//numero_txt.setText("CP"+(nb<10000?"0":"")+ (nb<1000?"0":"") + (nb<100?"0":"") + (nb<10?"0":"") + nb);
   		/*if(acg.dis==true) {
   		agio_txt.setDisable(true);
   		nomemp_txt.setDisable(true);
   		adressemp_txt.setDisable(true);
   		domainemp_txt.setDisable(true);
   		chooseimage.setDisable(true);
   		}*/	   		
   		

   		
   		cb_createur.setValue(entities.Employe.paramsCreateur);
   	}
   	
       
    @FXML
    void ajouter(ActionEvent event) {
    		int idAgence=0;
    		int idClient=0;
    		int idCreateur=0;
    		int idConseiller=0;
    		int agio=0;
    	    ic= new CompteDao();
    	    Compte c= new Compte() {
			};
			
			/*nb++;
			String numero= "CP"+(nb<10000?"0":"")+ (nb<1000?"0":"") + (nb<100?"0":"") + (nb<10?"0":"") + nb;			
	   		numero_txt.setText("CP"+(nb<10000?"0":"")+ (nb<1000?"0":"") + (nb<100?"0":"") + (nb<10?"0":"") + nb);
			*/
	   		/*String newnum = "";
	   		String sqlnum="select numero from compte";
	   		try {
				db.initPrepare(sqlnum);
				rs=db.executeSELECT();
				if(rs!=null)
				{
					while(rs.next())
					{	
						if(rs.getString("numero").equals(numero)) {
							nb++;
						}
						else {
							newnum=numero;
						}
						
					}
					
				}
				else
				{
					
				}
			}
				
			 catch (Exception e) {
				e.printStackTrace();
			}*/
	   		
			String numero = "CP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte();			
	   		numero_txt.setText("CP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte());		
			String solde = solde_txt.getText();
		
			LocalDate dt = dt_txt.getValue();
			String ph = phone_txt.getText();
			String in = ine_txt.getText();
			String mail = email_txt.getText();
			//int ag=Integer.parseInt(agio_txt.getText());
			try {
				rsA=ic.cbgetid("nom", "agence", "idA", cb_agence.getValue());
				while(rsA.next()) {
				idAgence=rsA.getInt("idA");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				rsCli=ic.cbgetid("INE", "client", "idC", ine_txt.getText());
				while(rsCli.next()) {
				idClient=rsCli.getInt("idC");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			

			try {
				rsCrea=ic.cbgetid("nom", "employe", "idE", cb_createur.getValue());
				while(rsCrea.next()) {
				idCreateur=rsCrea.getInt("idE");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
			try {
				rsCons=ic.cbgetid("nom", "employeur", "id", searchnomemp_txt.getText());
				while(rsCons.next()) {
				idConseiller=rsCons.getInt("id");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			//Image limg = new Image("C:\\Users\\HP\\eclipse-workspace\\Bank\\Picture\\Bank\\user4.png");
			
			String sqlpicver="Select * from client where INE=1";
			try {
				db.initPrepare(sqlpicver);
				rs=db.executeSELECT();
				if(rs!=null)
				{
					while(rs.next())
					{	
						 picver = new Image(rs.getBinaryStream("photo"));		
					}
				}
				else
				{
					
				}
			}
				
			 catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isANumber(solde) || Integer.parseInt(solde)<5000) { 

				Outil.showErrorMessage("Erreur", "Verifier le Solde *minimum Solde=5000 CFA*");
			}
			else if(dt_txt.getValue()==null){
		
				Outil.showErrorMessage("Erreur", "Verifier la Date");		
			}
			else if(cb_createur.getValue()==null){
				
				Outil.showErrorMessage("Erreur", "Choisir un Createur");		
			}
			
			else if(cb_agence.getValue()==null){
				
				Outil.showErrorMessage("Erreur", "Choisir une Agence");		
			}
			
			else if(!isANumber(in)){
				
				Outil.showErrorMessage("Erreur", "verifier INE");	
			}
			
			else if(fullname_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer un Nom Complet");		
			}
			
			else if(!isANumber(ph)){
				
				Outil.showErrorMessage("Erreur", "Entrer un Numero de Telephone");		
			}
			
			else if(job_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer une Profession");		
			}
			
			else if(adress_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer une Adresse pour le Client");		
			}
			
			else if(born_txt.getValue() == null){
					
				Outil.showErrorMessage("Erreur", "Entrer une Date de Naissance");		
			}
			
			else if(!isAnEmail(mail)){
				
				Outil.showErrorMessage("Erreur", "Entrer une Adresse mail");		
			}
		
			/*else if(selectedFile == null && labimage.getImage() == picver){				
				Outil.showErrorMessage("Erreur", "Choisir une Image");		
			}*/
			
			/*else if(searchnomemp_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Verifier Nom pour Rechercher Employeur");		
			}*/
			
			else if(nomemp_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Verifier Nom Complet Employeur");		
			}
			
			else if(adressemp_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Verifier Adresse Employeur");		
			}
			
			else if(domainemp_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Verifier Domaine Employeur");	
				
			}
				
			
								
			
			else {
				
				if(Integer.parseInt(solde) >= 75000 && Integer.parseInt(solde) <=150000){
					agio = 3000;
					}else if(Integer.parseInt(solde) <=300000){
						agio = 5000;
					}else if(Integer.parseInt(solde) <=600000){
						agio = 7000;
					}else if(Integer.parseInt(solde) <=900000){
						agio = 10000;
					}else{
						agio = 15000;
					}
				
				String sql="Select * from client where INE= '"+ine_txt.getText()+"'";
	        	String sql2="Select * from employeur where nom= '"+searchnomemp_txt.getText()+"'";
	    		try {
	    			db.initPrepare(sql);
	    			rs=db.executeSELECT();
	    			db.initPrepare(sql2);
	    			rs2=db.executeSELECT();
	    			while(rs.next()) {
	    				while(rs2.next()) {
	    			if(!rs.getString("INE").equals(ine_txt.getText())  && !rs2.getString("nom").equals(searchnomemp_txt.getText()))
	    			{
	    				String In= ine_txt.getText();	
				    	String nm=fullname_txt.getText().toString();
				    	String tl=phone_txt.getText();
				    	String jb=job_txt.getText().toString();
				    	String adr=adress_txt.getText().toString();
				    	LocalDate born=born_txt.getValue();
				    	String ml=email_txt.getText().toString();
				    	File file = new File(selectedFile.toURI());
				    	InputStream im = null;
				    	try {
							im = new FileInputStream(file);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	
				    	String nEmp=nomemp_txt.getText(); 
	    				String adr1=adressemp_txt.getText();
	    				String dom=domainemp_txt.getText();
	    				entities.Employeur emp = new entities.Employeur();
	    				emp.setNom(nEmp);
	    				emp.setAdr(adr1);
	    				emp.setDomaine(dom);
	    				int ep = ie.addEmployeur(emp);
	    				
				    	
				    	icli=new ClientDao();
			    		entities.Client cl = new entities.Client();
			    		cl.setINE(Integer.parseInt(In));
			    		cl.setNomcomplet(nm);
			    		cl.setTel(Integer.parseInt(tl));
			    		cl.setProfession(jb);
			    		cl.setAdresse(adr);
			    		cl.setDatenaiss(born);
			    		cl.setEmail(ml);
			    		cl.setPhoto(im);
			    		//cl.setIdEmp(ep);
			    		int clt =icli.add(cl);
			    		
			    		
	    				ic=new CompteDao();
			    		Courant cr = new Courant();
			    		
			    		c.setNumero(numero);
			    		c.setSolde(Integer.parseInt(solde));
			    		c.setDateouverture(LocalDate.now());
			    		c.setIdClient(clt);
			    		c.setIdCreateur(idCreateur);
			    		c.setIdConseille(ep);
			    		c.setIdAgence(idAgence);
			    		cr.setAgio(agio);
			    		c.setEtat("actif");
			    		int t =ic.add(c, "Courant");
			    		
			    		if(t != 0 && clt != 0 && ep!=0) {
				    		Outil.showConfirmationMessage("@2LK", "Un Nouveau Compte Courant a ete ajoute");
				    		Outil.showConfirmationMessage("@2LK", "Nouveau Client ajoute");
				    		Outil.showConfirmationMessage("@2LK", "Nouveau Employeur ajoute");
				    		debloquer();
				    		effacer();
				    		}else
				    		{
				    		Outil.showErrorMessage("Erreur", "Data not Added");	
				    		}
	    				
			    		
	    				
	    			}	
	    			else if(!rs.getString("INE").equals( ine_txt.getText()))
	    			{
	    				String In= ine_txt.getText();	
				    	String nm=fullname_txt.getText().toString();
				    	String tl=phone_txt.getText();
				    	String jb=job_txt.getText().toString();
				    	String adr=adress_txt.getText().toString();
				    	LocalDate born=born_txt.getValue();
				    	String ml=email_txt.getText().toString();
				    	File file = new File(selectedFile.toURI());
				    	InputStream im = null;
				    	try {
							im = new FileInputStream(file);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	
				    	icli=new ClientDao();
			    		entities.Client cl = new entities.Client();
			    		cl.setINE(Integer.parseInt(In));
			    		cl.setNomcomplet(nm);
			    		cl.setTel(Integer.parseInt(tl));
			    		cl.setProfession(jb);
			    		cl.setAdresse(adr);
			    		cl.setDatenaiss(born);
			    		cl.setEmail(ml);
			    		cl.setPhoto(im);
			    		//cl.setIdEmp(idConseiller);
			    		int clt =icli.add(cl);
			    	
			    	
			    		
			    		ic=new CompteDao();
			    		Courant cr = new Courant();
			    		
			    		c.setNumero(numero);
			    		c.setSolde(Integer.parseInt(solde));
			    		c.setDateouverture(LocalDate.now());
			    		c.setIdClient(clt);
			    		c.setIdCreateur(idCreateur);
			    		c.setIdConseille(idConseiller);
			    		c.setIdAgence(idAgence);
			    		cr.setAgio(agio);
			    		c.setEtat("actif");
			    		int t =ic.add(c, "Courant");
			    		
			    		if(t != 0 && clt != 0) {
			    		Outil.showConfirmationMessage("@2LK", "Un nouveau Compte Courant a ete ajoute");
			    		Outil.showConfirmationMessage("@2LK", "Nouveau Client ajoute");
			    		debloquer();
			    		effacer();
			    		}else
			    		{
			    		Outil.showErrorMessage("Erreur", "Data not Added");	
			    		}
	    			}
	    			
	    			else if(!rs2.getString("nom").equals(searchnomemp_txt.getText())) {
	    				String nEmp=nomemp_txt.getText(); 
	    				String adr=adressemp_txt.getText();
	    				String dom=domainemp_txt.getText();
	    				entities.Employeur emp = new entities.Employeur();
	    				emp.setNom(nEmp);
	    				emp.setAdr(adr);
	    				emp.setDomaine(dom);
	    				int ep = ie.addEmployeur(emp);
	    				try {
							rsCons=ic.cbgetid("nom", "employeur", "id", nEmp);
							while(rsCons.next()) {
							idConseiller=rsCons.getInt("id");
							}
							} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						
						}
	    				
	    				ic=new CompteDao();
			    		Courant cr = new Courant();
			    		
			    		c.setNumero(numero);
			    		c.setSolde(Integer.parseInt(solde));
			    		c.setDateouverture(LocalDate.now());
			    		c.setIdClient(idClient);
			    		c.setIdCreateur(idCreateur);
			    		c.setIdConseille(ep);
			    		c.setIdAgence(idAgence);
			    		cr.setAgio(agio);
			    		c.setEtat("actif");
			    		int t =ic.add(c, "Courant");
			    		
			    		if(t != 0 && ep != 0) {
			    		Outil.showConfirmationMessage("@2LK", "Un nouveau Compte Courant a ete ajoute");
			    		Outil.showConfirmationMessage("@2LK", "Nouveau Emlpoyeur ajoute");
			    		debloquer();
			    		effacer();
			    		}else
			    		{
			    		Outil.showErrorMessage("Erreur", "Data not Added");	
			    		}
	    			}
	    			
	    			else 
	    			{

	    		    	ic=new CompteDao();
	    				Courant cr = new Courant();
	    				
	    				c.setNumero(numero);
	    				c.setSolde(Integer.parseInt(solde));
	    				c.setDateouverture(LocalDate.now());
	    				c.setIdClient(idClient);
	    				c.setIdCreateur(idCreateur);
	    				c.setIdConseille(idConseiller);
	    				c.setIdAgence(idAgence);
	    				cr.setAgio(agio);
	    				c.setEtat("actif");
	    				int t =ic.add(c, "Courant");
	    				
	    				if(t !=0) {
	    				Outil.showConfirmationMessage("@2LK", "Un nouveau Compte Courant a ete ajoute");
	    				debloquer();
	    				effacer();
	    				}else
	    				{
	    				Outil.showErrorMessage("Erreur", "Data not Added");	
	    				}
	    				
	    			}
	    		}
	    		}
			}
	    		 catch (Exception e) {
	    			e.printStackTrace();
	    		}
				
		
			}
			
			
			
    }
    
    
    
    @FXML
    void ajouter2(ActionEvent event) {
    		int idAgence=0;
    		int idClient=0;
    		int idCreateur=0;
    		/*int idConseiller=0;*/
    	    ic= new CompteDao();
    	    Compte c= new Compte() {
			};
			
			/*nb++;
			String numero= "CP"+(nb<10000?"0":"")+ (nb<1000?"0":"") + (nb<100?"0":"") + (nb<10?"0":"") + nb;			
			*/
			
			String numero = "CP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte();			
	   		numero_txt.setText("CP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte());	
			
	   		String solde = solde_txt.getText();
			LocalDate dt = dt_txt.getValue();
			String ph = phone_txt.getText();
			String in = ine_txt.getText();
			String mail = email_txt.getText();
			try {
				rsA=ic.cbgetid("nom", "agence", "idA", cb_agence.getValue());
				while(rsA.next()) {
				idAgence=rsA.getInt("idA");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				rsCli=ic.cbgetid("INE", "client", "idC", ine_txt.getText());
				while(rsCli.next()) {
				idClient=rsCli.getInt("idC");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			

			try {
				rsCrea=ic.cbgetid("nom", "employe", "idE", cb_createur.getValue());
				while(rsCrea.next()) {
				idCreateur=rsCrea.getInt("idE");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
			/*try {
				rsCons=ic.cbgetid("nom", "employe", "idE", cb_conseiller.getValue());
				while(rsCons.next()) {
				idConseiller=rsCons.getInt("idE");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}*/
			
			if(!isANumber(solde) || Integer.parseInt(solde)<5000) { 
				Outil.showErrorMessage("Erreur", "Verifier le Solde *minimum Solde=5000 CFA*");
			}
			else if(dt_txt.getValue()==null){
		
				Outil.showErrorMessage("Erreur", "Verifier la Date");		
			}
			else if(cb_createur.getValue()==null){
				
				Outil.showErrorMessage("Erreur", "Choisir un Createur");		
			}
			
			else if(cb_agence.getValue()==null){
				
				Outil.showErrorMessage("Erreur", "Choisir une Agence");		
			}
			
			else if(!isANumber(in)){
				
				Outil.showErrorMessage("Erreur", "verifier INE");		
			}
			
			else if(fullname_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer un Nom Complet");		
			}
			
			else if(!isANumber(ph)){
				
				Outil.showErrorMessage("Erreur", "Entrer un Numero de Telephone");		
			}
			
			else if(job_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer une Profession");		
			}
			
			else if(adress_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer une Adresse pour le Client");		
			}
			
			else if(born_txt.getValue() == null){
				
				Outil.showErrorMessage("Erreur", "Entrer une Date de Naissance");		
			}
			
			else if(!isAnEmail(mail)){
				
				Outil.showErrorMessage("Erreur", "Entrer une Adresse mail");		
			}
			else {		
			
			String sql="Select * from client where INE= '"+ine_txt.getText()+"'";
	    	
			try {
				db.initPrepare(sql);
				rs=db.executeSELECT();
				while(rs.next())
				{
					if(rs.getString("INE").equals(ine_txt.getText())) {
					ic=new CompteDao();
		    		Simple sp=new Simple();
		    		c.setNumero(numero);
		    		c.setSolde(Integer.parseInt(solde));
		    		c.setDateouverture(LocalDate.now());
		    		c.setIdClient(idClient);
		    		c.setIdCreateur(idCreateur);
		    		c.setIdAgence(idAgence);
		    		c.setEtat("actif");
		    		int t =ic.add(c, "Simple");
		    		if(t !=0) {
		    		Outil.showConfirmationMessage("@2LK", "Un nouveau Compte Simple a ete ajoute");
		    		fullname_txt.setDisable(false);
		    		phone_txt.setDisable(false);
		    		job_txt.setDisable(false);
		    		adress_txt.setDisable(false);
		    		born_txt.setDisable(false);
		    		email_txt.setDisable(false);
		    		labimage.setDisable(false);
		    		ine_txt.setDisable(false);
		    		btn_picture.setDisable(false);
		    		effacerCpsimple();
		    	}else
		    	{
		    		Outil.showErrorMessage("Erreur", "Data not Added");	
		    	}
					}else
					{
						String In = ine_txt.getText();	
				    	String nm=fullname_txt.getText().toString();
				    	String tl=phone_txt.getText();
				    	String jb=job_txt.getText().toString();
				    	String adr=adress_txt.getText().toString();
				    	LocalDate born=born_txt.getValue();
				    	String ml=email_txt.getText().toString();
				    	File file = new File(selectedFile.toURI());
				    	InputStream im = null;
				    	try {
							im = new FileInputStream(file);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    	
				    	icli=new ClientDao();
			    		entities.Client cl = new entities.Client();
			    		cl.setINE(Integer.parseInt(In));
			    		cl.setNomcomplet(nm);
			    		cl.setTel(Integer.parseInt(tl));
			    		cl.setProfession(jb);
			    		cl.setAdresse(adr);
			    		cl.setDatenaiss(born);
			    		cl.setEmail(ml);
			    		cl.setPhoto(im);
			    		//cl.setIdEmp(0);
			    		int clt =icli.add(cl);
						

						ic=new CompteDao();
			    		Simple sp=new Simple();
			    		sp.setNumero(numero);
			    		sp.setSolde(Integer.parseInt(solde));
			    		sp.setDateouverture(LocalDate.now());
			    		sp.setIdClient(clt);
			    		sp.setIdCreateur(idCreateur);
			    		/*sp.setIdConseille(idConseiller);*/
			    		sp.setIdAgence(idAgence);
			    		sp.setEtat("actif");
			    		int t =ic.add(sp, "Simple");
			    		
			    		
			    		if(t !=0 && clt!=0) {
			    		Outil.showConfirmationMessage("@2LK", "Un nouveau Compte Simple a ete ajoute");
			    		Outil.showConfirmationMessage("@2LK", "Un nouveau Client a ete ajoute");
			    		effacerCpsimple();
	    				
			    	}else
			    	{
			    		Outil.showErrorMessage("Erreur", "Data not Added");	
			    	}	
					}

					
				}
			}
				
			 catch (Exception e) {
				e.printStackTrace();
			}
						
			}
    }

    @FXML
    void ajouter3(ActionEvent event) {
    		int idAgence = 0;
    		int idClient = 0;
    		int idCreateur =0;
    		/*int idConseiller =0;*/
    	    ic= new CompteDao();
    	    Compte c= new Compte() {
			};
			
			/*nb++;
			String numero= "CP"+(nb<10000?"0":"")+ (nb<1000?"0":"") + (nb<100?"0":"") + (nb<10?"0":"") + nb;			
			*/
			
			String numero = "CP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte();			
	   		numero_txt.setText("CP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte());		
			String solde=solde_txt.getText();		
			LocalDate dt = dt_txt.getValue();
			String ph = phone_txt.getText();
			String in = ine_txt.getText();
			String mail = email_txt.getText();
			try {
				rsA=ic.cbgetid("nom", "agence", "idA", cb_agence.getValue());
				while(rsA.next()) {
				idAgence=rsA.getInt("idA");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				rsCli=ic.cbgetid("INE", "client", "idC", ine_txt.getText());
				while(rsCli.next()) {
				idClient=rsCli.getInt("idC");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			//System.out.println(idClient);	

			try {
				rsCrea=ic.cbgetid("nom", "employe", "idE", cb_createur.getValue());
				while(rsCrea.next()) {
				idCreateur=rsCrea.getInt("idE");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
			/*try {
				rsCons=ic.cbgetid("nom", "employe", "idE", cb_conseiller.getValue());
				while(rsCons.next()) {
				idConseiller=rsCons.getInt("idE");
				}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			/*int In= Integer.parseInt(ine_txt.getText());	
	    	String nm=fullname_txt.getText().toString();
	    	int tl=Integer.parseInt(phone_txt.getText());
	    	String jb=job_txt.getText().toString();
	    	String adr=adress_txt.getText().toString();
	    	LocalDate born=born_txt.getValue();
	    	String ml=email_txt.getText().toString();
	    	File file = new File(selectedFile.toURI());
	    	InputStream im = null;
	    	try {
				im = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	icli=new ClientDao();
    		entities.Client cl = new entities.Client();
    		cl.setINE(In);
    		cl.setNomcomplet(nm);
    		cl.setTel(tl);
    		cl.setProfession(jb);
    		cl.setAdresse(adr);
    		cl.setDatenaiss(born);
    		cl.setEmail(ml);
    		cl.setPhoto(im);*/
    		//int clt =icli.add(cl);
			

			
			
			if(!isANumber(solde) || Integer.parseInt(solde)<5000) { 
				Outil.showErrorMessage("Erreur", "Verifier le Solde *minimum Solde=5000 CFA*");
			}
			else if(dt_txt.getValue()==null){
		
				Outil.showErrorMessage("Erreur", "Verifier la Date");		
			}
			else if(cb_createur.getValue()==null){
				
				Outil.showErrorMessage("Erreur", "Choisir un Createur");		
			}
			
			else if(cb_agence.getValue()==null){
				
				Outil.showErrorMessage("Erreur", "Choisir une Agence");		
			}
			
			else if(!isANumber(in)){
				
				Outil.showErrorMessage("Erreur", "verifier INE");		
			}
			
			else if(fullname_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer un Nom Complet");		
			}
			
			else if(!isANumber(ph)){
				
				Outil.showErrorMessage("Erreur", "Entrer un Numero de Telephone");		
			}
			
			else if(job_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer une Profession");		
			}
			
			else if(adress_txt.getText().trim().equals("")){
				
				Outil.showErrorMessage("Erreur", "Entrer une Adresse pour le Client");		
			}
			
			else if(born_txt.getValue() == null){
				
				Outil.showErrorMessage("Erreur", "Entrer une Date de Naissance");		
			}
			
			else if(!isAnEmail(mail)){
				
				Outil.showErrorMessage("Erreur", "Entrer une Adresse mail");		
			}
			else {
	    	
			String sql="Select * from client where INE= '"+ine_txt.getText()+"'";
	    	
			try {
				db.initPrepare(sql);
				rs=db.executeSELECT();
				while(rs.next())
				{
					if(rs.getString("INE").equals(ine_txt.getText())) {
					ic=new CompteDao();
		    		Teranga tr=new Teranga();
		    		//Teranga tr2=new Teranga();
		    		c.setNumero(numero);
		    		c.setSolde(Integer.parseInt(solde));
		    		c.setDateouverture(LocalDate.now());
		    		c.setIdClient(idClient);
		    		c.setIdCreateur(idCreateur);
		    		//tr.setIdConseille(idConseiller);
		    		c.setIdAgence(idAgence);
		    		c.setEtat("actif");
		    		int t =ic.add(c, "Teranga");
		    		
		    		
		    		if(t !=0) {
		    		Outil.showConfirmationMessage("@2LK", "Un nouveau Compte Teranga a ete ajoute");
		    		fullname_txt.setDisable(false);
		    		phone_txt.setDisable(false);
		    		job_txt.setDisable(false);
		    		adress_txt.setDisable(false);
		    		born_txt.setDisable(false);
		    		email_txt.setDisable(false);
		    		labimage.setDisable(false);
		    		ine_txt.setDisable(false);
		    		btn_picture.setDisable(false);
		    		effacerCpsimple();
		    	}else
		    	{
		    		Outil.showErrorMessage("Erreur", "Data not Added");	
		    	}
					
				}else
				{
					System.out.println("Dieu merci");
					String In= ine_txt.getText();	
			    	String nm=fullname_txt.getText().toString();
			    	String tl=phone_txt.getText();
			    	String jb=job_txt.getText().toString();
			    	String adr=adress_txt.getText().toString();
			    	LocalDate born=born_txt.getValue();
			    	String ml=email_txt.getText().toString();
			    	File file = new File(selectedFile.toURI());
			    	InputStream im = null;
			    	try {
						im = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    	icli=new ClientDao();
		    		entities.Client cl = new entities.Client();
		    		cl.setINE(Integer.parseInt(In));
		    		cl.setNomcomplet(nm);
		    		cl.setTel(Integer.parseInt(tl));
		    		cl.setProfession(jb);
		    		cl.setAdresse(adr);
		    		cl.setDatenaiss(born);
		    		cl.setEmail(ml);
		    		cl.setPhoto(im);
		    		//cl.setIdEmp(0);
		    		int clt =icli.add(cl);
					
				
					ic=new CompteDao();
		    		Teranga tr=new Teranga();
		    		//Teranga tr2=new Teranga();
		    		tr.setNumero(numero);
		    		tr.setSolde(Integer.parseInt(solde));
		    		tr.setDateouverture(LocalDate.now());
		    		tr.setIdClient(clt);
		    		tr.setIdCreateur(idCreateur);
		    		//tr.setIdConseille(idConseiller);
		    		tr.setIdAgence(idAgence);
		    		tr.setEtat("actif");
		    		int t =ic.add(tr, "Teranga");
		    		
		    		
		    		if(t !=0 && clt!=0) {
		    		Outil.showConfirmationMessage("@2LK", "Un nouveau Compte Teranga a ete ajoute");
		    		Outil.showConfirmationMessage("@2LK", "Un nouveau Client a ete ajoute");
		    		effacerCpsimple();
		    	
		    	}else
		    	{
		    		Outil.showErrorMessage("Erreur", "Data not Added");	
		    	}	
				}
					
				}
				
			}
				
			 catch (Exception e) {
				e.printStackTrace();
			}
			}
			
    }
    	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	void fillinComboBoxAgence()
    {
    	String sql="select * from agence";
    	
    	
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			ObservableList<String> l_ag=FXCollections.observableArrayList();
			while(rs.next())
			{
				String nom=rs.getString("nom").toString();
				l_ag.add(nom);
			}
			cb_agence.setItems(l_ag);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	void fillinComboBoxClient()
       {
       	String sql="select * from client";
       	
       	
   		try {
   			db.initPrepare(sql);
   			rs=db.executeSELECT();
   			ObservableList<String> l_cli=FXCollections.observableArrayList();
   			while(rs.next())
   			{
   				String nom=rs.getString("nomcomplet").toString();
   				l_cli.add(nom);
   			}
   			cb_cli.setItems(l_cli);
   		} catch (SQLException e) {
   			
   			e.printStackTrace();
   		}
       	
       }
    	
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	void fillinComboBoxEmploye()
       {
       	String sql="select * from employe";
       	
       	
   		try {
   			db.initPrepare(sql);
   			rs=db.executeSELECT();
   			ObservableList<String> l_emp=FXCollections.observableArrayList();
   			while(rs.next())
   			{
   				String nom=rs.getString("nom").toString();
   				l_emp.add(nom);
   			}
   			cb_conseiller.setItems(l_emp);
   			cb_createur.setItems(l_emp);
   		} catch (SQLException e) {
   			
   			e.printStackTrace();
   		}
       	
       }
    
    @FXML
    void ParcourirTeranga(ActionEvent event) {
    	fC = new FileChooser();
    	FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    	FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
    	fC.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
    	selectedFile = fC.showOpenDialog(null);
    	
    	if(selectedFile == null) {
    		Alert a=new Alert(AlertType.CONFIRMATION);
    		a.setTitle("BANK");
    		a.setContentText("Image, Choisissez une photo");
    		a.showAndWait();
    	}else {
    	Image img = new Image(selectedFile.toURI().toString());
    	labimage.setImage(img);
    	}
    }
    

    @FXML
    void ParcourirCourant(ActionEvent event) {
    	fC = new FileChooser();
    	FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    	FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
    	fC.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
    	selectedFile = fC.showOpenDialog(null);
    	
    	if(selectedFile == null) {
    		Alert a=new Alert(AlertType.CONFIRMATION);
    		a.setTitle("BANK");
    		a.setContentText("Image, Choisissez une photo");
    		a.showAndWait();
    	}
    	else {
    	Image img = new Image(selectedFile.toURI().toString());
    	labimage.setImage(img);
    }
    }
    
    @FXML
    void ParcourirSimple(ActionEvent event) {
    	fC = new FileChooser();
    	FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    	FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
    	fC.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
    	selectedFile = fC.showOpenDialog(null);
    	
    	if(selectedFile == null) {
    		Alert a=new Alert(AlertType.CONFIRMATION);
    		a.setTitle("BANK");
    		a.setContentText("Image, Choisissez une photo");
    		a.showAndWait();
    	}else {
    	Image img = new Image(selectedFile.toURI().toString());
    	labimage.setImage(img);
    }
    }

    @FXML
    void searchClientTeranga(ActionEvent event) {
    	
    	if(!ine_txt.getText().trim().equals("")) {
    	String sql="Select * from client where INE= '"+ine_txt.getText()+"'";
    	
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			if(rs!=null)
			{
				while(rs.next())
				{	
					fullname_txt.setText(rs.getString("nomcomplet"));
					phone_txt.setText(rs.getString("tel"));
					job_txt.setText(rs.getString("profession"));
					adress_txt.setText(rs.getString("adresse"));
					LocalDate dte = LocalDate.parse(rs.getString("datenaiss"));
					born_txt.setValue(dte);
					email_txt.setText(rs.getString("email"));
					Image pic = new Image(rs.getBinaryStream("photo"));
					labimage.setImage(pic);
					fullname_txt.setDisable(true);
					phone_txt.setDisable(true);
					job_txt.setDisable(true);
					adress_txt.setDisable(true);
					born_txt.setDisable(true);
					email_txt.setDisable(true);
					labimage.setDisable(true);
					ine_txt.setDisable(true);
					btn_picture.setDisable(true);
					cb_cli.setPromptText(rs.getString("nomcomplet"));
				}
			}
			else
			{
				
			}
		}
			
		 catch (Exception e) {
			e.printStackTrace();
		}
    }else {
		Outil.showErrorMessage("BANK", "Veuillez saisir un numero de carte");
    }
    	
    }
    
    

    @FXML
    void searchClientCourant(ActionEvent event) {
    	if(!ine_txt.getText().trim().equals("")) {
    	String sql="Select * from client where INE= '"+ine_txt.getText()+"'";
    	
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			if(rs!=null)
			{
				while(rs.next())
				{	
					fullname_txt.setText(rs.getString("nomcomplet"));
					phone_txt.setText(rs.getString("tel"));
					job_txt.setText(rs.getString("profession"));
					adress_txt.setText(rs.getString("adresse"));
					LocalDate dte = LocalDate.parse(rs.getString("datenaiss"));
					born_txt.setValue(dte);
					email_txt.setText(rs.getString("email"));
					Image pic = new Image(rs.getBinaryStream("photo"));
					labimage.setImage(pic);
					fullname_txt.setDisable(true);
					phone_txt.setDisable(true);
					job_txt.setDisable(true);
					adress_txt.setDisable(true);
					born_txt.setDisable(true);
					email_txt.setDisable(true);
					labimage.setDisable(true);
					ine_txt.setDisable(true);
					btn_picture.setDisable(true);
					cb_cli.setPromptText(rs.getString("nomcomplet"));
				}
			}
			else
			{
				
			}
		}
			
		 catch (Exception e) {
			e.printStackTrace();
		}
    }else {
		Outil.showErrorMessage("BANK", "Veuillez saisir un numero de carte");
    }
    	
    }
    

    @FXML
    void searchClientSimple(ActionEvent event) {
    	if(!ine_txt.getText().trim().equals("")) {
        	String sql="Select * from client where INE= '"+ine_txt.getText()+"'";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			if(rs!=null)
    			{
    				while(rs.next())
    				{	
    					fullname_txt.setText(rs.getString("nomcomplet"));
    					phone_txt.setText(rs.getString("tel"));
    					job_txt.setText(rs.getString("profession"));
    					adress_txt.setText(rs.getString("adresse"));
    					LocalDate dte = LocalDate.parse(rs.getString("datenaiss"));		
    					born_txt.setValue(dte);
    					email_txt.setText(rs.getString("email"));
    					Image pic = new Image(rs.getBinaryStream("photo"));
    					labimage.setImage(pic);
    					fullname_txt.setDisable(true);
    					phone_txt.setDisable(true);
    					job_txt.setDisable(true);
    					adress_txt.setDisable(true);
    					born_txt.setDisable(true);
    					email_txt.setDisable(true);
    					labimage.setDisable(true);
    					ine_txt.setDisable(true);
    					btn_picture.setDisable(true);
    					cb_cli.setPromptText(rs.getString("nomcomplet"));
    				}
    			}
    			else
    			{
    				
    			}
    		}
    			
    		 catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
    		Outil.showErrorMessage("BANK", "Veuillez saisir un numero de carte");
        }
    }

    @FXML
    void searchEmployeurCourant(ActionEvent event) {
    	if(!searchnomemp_txt.getText().trim().equals("")) {
    	String sql="Select * from employeur where nom= '"+searchnomemp_txt.getText()+"'";
    	
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			if(rs!=null)
			{
				while(rs.next())
				{	
					nomemp_txt.setText(rs.getString("nom"));
					adressemp_txt.setText(rs.getString("adresse"));
					domainemp_txt.setText(rs.getString("domaine"));
					nomemp_txt.setDisable(true);
					searchnomemp_txt.setDisable(true);
					adressemp_txt.setDisable(true);
					domainemp_txt.setDisable(true);
					cb_conseiller.setPromptText(rs.getString("nom"));
				}
			}
			else
			{	
				
			}
		}
			
		 catch (Exception e) {
			e.printStackTrace();
		}
    }else {
		Outil.showErrorMessage("BANK", "Veuillez saisir un nom");
    }
    	
    }

    
    @FXML
    void logout(ActionEvent event) throws IOException {
    
    	String url="/view/Login.fxml";
		Outil.load(event, url);
    }
   
    @FXML
    void back(ActionEvent event) throws IOException{
    	String url="/view/AccueilGerant.fxml";
		Outil.load(event, url);
    }
    
    
    public void debloquer() {
    	fullname_txt.setDisable(false);
		phone_txt.setDisable(false);
		job_txt.setDisable(false);
		adress_txt.setDisable(false);
		born_txt.setDisable(false);
		email_txt.setDisable(false);
		labimage.setDisable(false);
		ine_txt.setDisable(false);
		btn_picture.setDisable(false);
		nomemp_txt.setDisable(false);
		searchnomemp_txt.setDisable(false);
		adressemp_txt.setDisable(false);
		domainemp_txt.setDisable(false);
    }
    
    public void bloquer() {
    	fullname_txt.setDisable(true);
		phone_txt.setDisable(true);
		job_txt.setDisable(true);
		adress_txt.setDisable(true);
		born_txt.setDisable(true);
		email_txt.setDisable(true);
		labimage.setDisable(true);
		ine_txt.setDisable(true);
		btn_picture.setDisable(true);
    }
    
    public void effacer() {
    	solde_txt.setText("");
    	fullname_txt.setText("");
		phone_txt.setText("");
		job_txt.setText("");
		adress_txt.setText("");
		born_txt.setPromptText("");
		email_txt.setText("");
		dt_txt.setPromptText("");
		
		String sql="Select * from client where INE=1";
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			if(rs!=null)
			{
				while(rs.next())
				{	
					Image pic = new Image(rs.getBinaryStream("photo"));
					labimage.setImage(pic);
				}
			}
			else
			{
				
			}
		}
			
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		ine_txt.setText("");
		nomemp_txt.setText("");
		adressemp_txt.setText("");
		domainemp_txt.setText("");
		searchnomemp_txt.setText("");
		cb_agence.setPromptText("Agence");
		cb_createur.setPromptText("Createur");
		cb_cli.setPromptText("Client");
		cb_conseiller.setPromptText("Conseille");
	}
    
    public void effacerCpsimple() {
    	solde_txt.setText("");
    	fullname_txt.setText("");
		phone_txt.setText("");
		job_txt.setText("");
		adress_txt.setText("");
		born_txt.setPromptText("");
		email_txt.setText("");
		
		
		String sql="Select * from client where INE=1";
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			if(rs!=null)
			{
				while(rs.next())
				{	
					Image pic = new Image(rs.getBinaryStream("photo"));
					labimage.setImage(pic);
				}
			}
			else
			{
				
			}
		}
			
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		ine_txt.setText("");
	}
    
    public boolean isANumber(String s) {
    	if(s==null) return false;
    	try {
			new java.math.BigDecimal(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
    }
    
    public boolean isAnEmail(String s) {
    	
    	if(s==null) 
    	{
    		return false;
    	}else if(Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", s)) {
    		return true;			
    	}else {
    		return false;
    	}	
    }
    
    public boolean isAdate(String s) {
    	String date_finale;
    	String jour;
    	String mois;
    	String annee;
    	
    	jour = s.substring(0, 1)+s.substring(1,2);
    	mois = s.substring(3, 4)+s.substring(4,5);
    	annee = s.substring(6, 7)+s.substring(7,8)+s.substring(8,9)+s.substring(9,10);
    	
    	date_finale = jour + "/" + mois + "/" + annee;
    	String DatePattern = "^(?!(31)(\\D)(0?[13578]|1[02])\\2|(29|30)(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|^(29)(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$";
    	
    	if(date_finale.matches(DatePattern))
		{
			return true;
		}
		else {
			
			return false;
		}
		}
    
    public int numeroCompte() {
    	int lower =1;
    	int higher = 10000;
    	int random = (int)(Math.random() * (higher-lower)) + lower;
    	return random;
    }
		
    }
    
