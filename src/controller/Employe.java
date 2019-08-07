package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import entities.Agence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import method.ICompte;
import method.IEmploye;
import method.Outil;
import model.CompteDao;
import model.DB;
import model.EmployeDao;

public class Employe implements Initializable{

    @FXML
    private TextField nom_txt;

    @FXML
    private TextField tel_txt;
    
    @FXML
    private Button nouveau_btn;

    @FXML
    private Button enregistrer_btn;

    @FXML
    private TableView<?> listeEmploye;

    @FXML
    private Button modifier_btn;

    @FXML
    private Button supprimer_btn;
    
    @FXML
    private ComboBox<String> cb_poste;

    @FXML
    private ComboBox<String> cb_agence;

    @FXML
    private ComboBox<String> cb_agenceview;
    @FXML
    private ImageView labimage;
    @FXML
    FileInputStream fIS;
    @FXML
    FileChooser fC;
    @FXML
    File selectedFile;
    private DB db;
	private IEmploye ie;
	private ICompte ic;
	private ResultSet rs;
	private ResultSet rsPo;
	private ResultSet rsAg;
	private boolean bol;
    public Employe()
    {
    	db=new DB();
    }
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillinComboBoxAgence();
		fillinComboBoxPoste();
		
	}
    
    @FXML
    void parcourir(ActionEvent event) throws IOException {
    	
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
    void delete(ActionEvent event) {

    	
    }

    @FXML
    void modify(ActionEvent event) {

    }

    @FXML
    void nouv(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event){
    	ic = new CompteDao();
    	String numero = "EMP"+(numeroCompte()<10000?"0":"")+ (numeroCompte()<1000?"0":"") + (numeroCompte()<100?"0":"") + (numeroCompte()<10?"0":"") + numeroCompte();			
   		String nom =nom_txt.getText();
    	String tl=tel_txt.getText();
    	int idposte=0;
    	int idagence=0;
    	//System.out.println(cb_poste.getValue());
    	try {
			rsPo=ic.cbgetid("libelleP", "poste", "idP", cb_poste.getValue());
			while(rsPo.next()) {
			idposte=rsPo.getInt("idP");
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	try {	
			rsAg=ic.cbgetid("nom", "agence", "idA", cb_agence.getValue());
			while(rsAg.next()) {
			idagence=rsAg.getInt("idA");
			}
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	
    	if(nom_txt.getText().trim().equals("")) {
    		Outil.showErrorMessage("BANK", "Verifier Le Nom");
    	}else if(!isANumber(tel_txt.getText())){
			Outil.showErrorMessage("Erreur", "Entrer un Numero de Telephone");		
		}else if(cb_poste.getValue()==null){
			
			Outil.showErrorMessage("Erreur", "Choisir un Poste");		
		}else if(cb_agence.getValue()==null){
			
			Outil.showErrorMessage("Erreur", "Choisir une Agence");		
		}else if(selectedFile == null){
			
			Outil.showErrorMessage("Erreur", "Choisir une Image");		
		}	
		else {
			
			File file = new File(selectedFile.toURI());
	    	InputStream im = null;
	    	try {
				im = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    	
    		ie=new EmployeDao();
    		entities.Employe em=new entities.Employe();
    		em.setMatricule(numero);
    		em.setNom(nom);
    		em.setTel(Integer.parseInt(tl));
    		em.setIdPoste(idposte);
    		em.setPassword("1");
    		em.setIdAgence(idagence);
    		em.setPhoto(im);
    		//em.setMatricule("HH");	
    		int t =ie.add(em);
    		if(t !=0) {
    		Outil.showConfirmationMessage("@2LK", "Added data");
    		} 		
    	else {
    		Outil.showErrorMessage("Erreur", "Data not Added");
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
			cb_agenceview.setItems(l_ag);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }
    
    void fillinComboBoxPoste()
    {
    	String sql="select * from poste";
    	
    	
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			ObservableList<String> l_po=FXCollections.observableArrayList();
			while(rs.next())
			{
				String nom=rs.getString("libelleP").toString();
				l_po.add(nom);
			}
			cb_poste.setItems(l_po);
		} catch (SQLException e) {
			
			e.printStackTrace();
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
    
    public boolean isANumber(String s) {
    	if(s==null) return false;
    	try {
			new java.math.BigDecimal(s);
			return true;
		} catch (NumberFormatException e) {
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
