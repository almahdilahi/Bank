package controller;
import java.io.IOException;

import entities.Employe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import method.IClient;
import method.IEmploye;
import method.Outil;
import model.ClientDao;
import model.EmployeDao;

public class Connexion {

    @FXML
    private TextField login_txt;

    @FXML
    private PasswordField password_txt;

    @FXML
    private Button connect_btn;

    @FXML
    void getConnexion(ActionEvent event) throws IOException {
    	String log=login_txt.getText();
		String pwd=password_txt.getText();
		if(log.trim().equals("") || pwd.trim().equals("")) {
			Outil.showErrorMessage("Erreur", "Veuillez remplir tous les champs!");
		}
		else {
			IEmploye ie=new EmployeDao();
			IClient icli=new ClientDao();
			Employe em=ie.getLoginUser(log, pwd);
			Employe em2=ie.getLoginAdmin(log, pwd);
			entities.Client cli=icli.getLoginClient(log, pwd);				
			if(em2!=null) {
				entities.Employe.paramsCreateur=em2.getNom();
				String url="/view/AccueilGerant.fxml";
				Outil.load(event, url);
			}
			else if(em!=null)
			{
				
				/*Alert a=new Alert(AlertType.CONFIRMATION);
				a.setTitle("BANK");
				a.setContentText("User Interface doesn't ready");
				a.showAndWait();
				*/
				entities.Operation.paramsCaissier=em.getNom();
				String url="/view/AccueilClient.fxml";
				Outil.load(event, url);
			
			}
			else {
				Outil.showErrorMessage("Erreur", "Login ou mot de passe incorrect!");
			}
			

    }

    }
}
