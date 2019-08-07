package controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import method.IClient;
import method.IEmploye;
import method.Outil;
import model.ClientDao;
import model.DB;

public class Client {
	
	private IClient ic;
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
    private Button btn_save;
    @FXML
    private ImageView labimage;
    private String path;
    @FXML
    FileInputStream fIS;
    @FXML
    FileChooser fC;
    @FXML
    File selectedFile;
    private DB db;
   	private ResultSet rs;
   	
   	 public Client() {
   		db=new DB();
	}
    
    @FXML
    void Parcourir(ActionEvent event) throws IOException {
    	/*JFileChooser fl=new JFileChooser();
    	FileNameExtensionFilter filter=new FileNameExtensionFilter("IMAGE","jpg","png","gif");
    	fl.addChoosableFileFilter(filter);
    	int result =fl.showSaveDialog(null);
    	if(result==JFileChooser.APPROVE_OPTION)
    	{
    		File slf = fl.getSelectedFile();
    		path = slf.getAbsolutePath();
    		Image img;
    		img = new Image(slf.toURI().toString(),100,150,true,true);
    		labimage.setImage(img);
    		ImageView imgView;
    		imgView = new ImageView(img);
    		imgView.setFitHeight(150);
    		imgView.setFitWidth(100);
    		imgView.setPreserveRatio(true);
    		ImageIcon myImage = new ImageIcon(path);
    		java.awt.Image img = myImage.getImage();
    		java.awt.Image newImage = img.getScaledInstance(1, 2, 3);
    		ImageIcon finalImg = new ImageIcon(newImage);
    		
    	}else
    	{
    		if(result == JFileChooser.CANCEL_OPTION)
    			JOptionPane.showMessageDialog(null, "No choice");;
    	}*/
    	
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

    @SuppressWarnings("unused")
	@FXML
    void ajouter(ActionEvent event) {
    	
    	String In = ine_txt.getText();	
    	String nm = fullname_txt.getText();
    	String tl = phone_txt.getText();
    	String jb =job_txt.getText().toString();
    	String adr = adress_txt.getText();
    	LocalDate dt = born_txt.getValue();
    	String ml = email_txt.getText();
    	//Image pp=labimage.getImage();
    	//InputStream im = new FileInputStream(new File(path));
    	
    	if(!isANumber(ine_txt.getText())) {
    	Outil.showErrorMessage("BANK", "Verifier INE");
    	}
    	else if(fullname_txt.getText().trim().equals("") ) {
    		Outil.showErrorMessage("BANK", "Verifier Le Nom");
    	}
    	else if(!isANumber(phone_txt.getText())){
			Outil.showErrorMessage("Erreur", "Entrer un Numero de Telephone");		
		}
    	else if(job_txt.getText().trim().equals("") ) {
    		Outil.showErrorMessage("BANK", "Verifie la profession");
    	}
    	else if(adress_txt.getText().trim().equals("") ) {
    		Outil.showErrorMessage("BANK", "Verifier Adresse");
    	}
    	else if(born_txt.getValue()==null){
    		
			Outil.showErrorMessage("Erreur", "Verifier la Date de naissance");		
		}else if(!isAnEmail(email_txt.getText())){
			
			Outil.showErrorMessage("Erreur", "Entrer une Adresse mail");		
		}else if(selectedFile == null){
			
			Outil.showErrorMessage("Erreur", "Choisir une Image");		
		}else {
			File file = new File(selectedFile.toURI());
	    	InputStream im = null;
	    	try {
				im = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    	
   
    		ic=new ClientDao();
    		entities.Client cl = new entities.Client();
    		cl.setINE(Integer.parseInt(In));
    		cl.setNomcomplet(nm);
    		cl.setTel(Integer.parseInt(tl));
    		cl.setProfession(jb);
    		cl.setAdresse(adr);
    		cl.setDatenaiss(dt);
    		cl.setEmail(ml);
    		cl.setPhoto(im);
    		//cl.setIdEmp(0);
    		int t =ic.add(cl);
    		if(t !=0) {
    		Outil.showConfirmationMessage("@2LK", "Added data");
    		clean();
    		} 		
    	else {
    		Outil.showErrorMessage("Erreur", "Data not Added");
    	}
    	
    	
		}
    }
    
    void clean() {
    	ine_txt.setText("");
    	fullname_txt.setText("");
    	phone_txt.setText("");
    	job_txt.setText("");
    	adress_txt.setText("");
    	born_txt.setValue(null);
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
}
