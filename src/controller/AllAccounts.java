package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import method.ICompte;
import method.Outil;
import model.CompteDao;


public class AllAccounts implements Initializable{

    @FXML
    private TableView<Compte> accounts_table;

    @FXML
    private TableColumn<Compte, Integer> idcompte;

    @FXML
    private TableColumn<Compte, String> numero;

    @FXML
    private TableColumn<Compte, Integer> solde;

    @FXML
    private TableColumn<Compte, LocalDate> dateouv;

    @FXML
    private TableColumn<Compte, Integer> idclient;

    @FXML
    private TableColumn<Compte, Integer> idcreateur;

    @FXML
    private TableColumn<Compte, Integer> idconseille;

    @FXML
    private TableColumn<Compte, Integer> idagence;

    @FXML
    private TableColumn<Compte, Integer> agio;

    @FXML
    private TableColumn<Compte, Integer> fraisouv;

    @FXML
    private TableColumn<Compte, Float> tauxrenum;

    @FXML
    private TableColumn<Compte, String> etat;

    @FXML
    private TableColumn<Compte, String> type;
    
    private ICompte ic;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idcompte.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
		numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
		solde.setCellValueFactory(new PropertyValueFactory<>("solde"));
		dateouv.setCellValueFactory(new PropertyValueFactory<>("dtouv"));
		idclient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		idcreateur.setCellValueFactory(new PropertyValueFactory<>("idCreateur"));
		idconseille.setCellValueFactory(new PropertyValueFactory<>("idConseille"));
		idagence.setCellValueFactory(new PropertyValueFactory<>("idAgence"));
		agio.setCellValueFactory(new PropertyValueFactory<>("Agio"));
		fraisouv.setCellValueFactory(new PropertyValueFactory<>("fraisouv"));
		tauxrenum.setCellValueFactory(new PropertyValueFactory<>("tauxren"));
		etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		loadTable();
		
	}
	
	private void loadTable() {
		ic=new CompteDao();
		ObservableList<Compte> l_compte=FXCollections.observableArrayList();
		ic.liste().stream().forEach(u->l_compte.add(u));
		//Data loading
		accounts_table.setItems(l_compte);
	}

    
	@FXML
    void back(ActionEvent event) throws IOException {
    	String url="/view/Consultation.fxml";
		Outil.load(event, url);
    }
	
	  @FXML
	    void logout(ActionEvent event) throws IOException {
	    	String url="/view/Login.fxml";
			Outil.load(event, url);
	    }
    
    
}
