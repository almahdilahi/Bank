package controller;

import java.io.IOException;

import entities.Compte;
import entities.Courant;
import entities.Simple;
import entities.Teranga;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import method.Outil;

public class AccueilGerant {

    @FXML
    private Button btn_client;

    @FXML
    private MenuItem btn_courant;

    @FXML
    private MenuItem btn_simple;

    @FXML
    private MenuItem btn_teranga;

    @FXML
    private Button btn_employee;

    //public Boolean dis=false;
    @FXML
    public void getAccountCourant(ActionEvent event) throws IOException {
    	String url="/view/CompteFx.fxml";
		Outil.loadMenuItem(event, url);
		
		//dis=false;
    }
    
    @FXML
    public void getAccountTeranga(ActionEvent event) throws IOException {
    	String url="/view/CompteFx3.fxml";
		Outil.loadMenuItem(event, url);
		//dis=true;
    }
    
    @FXML
    public void getAccountSimple(ActionEvent event) throws IOException {
    	String url="/view/CompteFx2.fxml";
		Outil.loadMenuItem(event, url);
		//dis=true;
    }

    @FXML
    void getClient(ActionEvent event) throws IOException {
    	String url="/view/ClientFx.fxml";
		Outil.load(event, url);
    }

    @FXML
    void getEmployee(ActionEvent event) throws IOException {
    	String url="/view/EmployeFx.fxml";
		Outil.load(event, url);
    }
    
   

}
