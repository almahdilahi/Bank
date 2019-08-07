package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import method.Outil;

public class AccueilClient {
	


	@FXML
    void getClonsultation(ActionEvent event) throws IOException {
    	String url="/view/Consultation.fxml";
    	Outil.load(event, url);
    }

    @FXML
    void getEmployee(ActionEvent event) {

    }

    @FXML
    void getTansactionDepot(ActionEvent event) throws IOException {
    	String url="/view/Depot.fxml";
		Outil.loadMenuItem(event, url);
	
	
    }

    @FXML
    void getTansactionRetrait(ActionEvent event) throws IOException {
    	String url="/view/Retrait.fxml";
		Outil.loadMenuItem(event, url);
		
    }

    @FXML
    void getTansactionVirement(ActionEvent event) throws IOException {
    	String url="/view/Virement.fxml";
		Outil.loadMenuItem(event, url);
	
    }
    
    

	

}
