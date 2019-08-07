package controller;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import method.IClient;
import method.ICompte;
import method.IEmploye;
import method.Outil;
import model.DB;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.util.PublicCloneable;

import com.itextpdf.text.pdf.PdfWriter;
public class Consultation {

    @FXML
    private TextField numerocomptesearch;

    @FXML
    private Button jobcli;

    @FXML
    private Button borncli;

    @FXML
    private Button adresscli;

    @FXML
    private Button mailcli;

    @FXML
    private ImageView photocli;

    @FXML
    private Button nomcli;

    @FXML
    private Button phonecli;

    @FXML
    private Button numerocompte;

    @FXML
    private Button solde;

    @FXML
    private Button etat;

    @FXML
    private Button fraisouv;

    @FXML
    private Button type;

    @FXML
    private Button agio;

    @FXML
    private Button dateouv;

    @FXML
    private Button tauxren;

    @FXML
    private Button nomagence;

    @FXML
    private Button adresseagence;

    @FXML
    private Button nomconseille;

    @FXML
    private Button adresseconseille;

    @FXML
    private Button domaineconseille;

    @FXML
    private Button nomcreateur;

    @FXML
    private Button adressecreateur;

    @FXML
    private Button postecrea;
    @FXML
    private Button phonecreateur;
    @FXML
    private ImageView photocreateur;
    
    @FXML
    private DatePicker datedep;

    @FXML
    private DatePicker datefin;
    private static String numSe=null; 
    private DB db;
   	private ResultSet rs=null;  
	private ResultSet rs2=null;
   	public Consultation()
       {
       	db=new DB();
       }

    @FXML
    void activer(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
    	String url="/view/AccueilClient.fxml";
		Outil.load(event, url);
    }
    @FXML
    void back1(ActionEvent event) throws IOException {
    	String url="/view/Consultation.fxml";
		Outil.load(event, url);
    }

    @FXML
    void bloquer(ActionEvent event) {

    }

    @FXML
    void fermer(ActionEvent event) {

    }

    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	String url="/view/Login.fxml";
		Outil.load(event, url);
    }
    
    @FXML
    void verslistcomptes(ActionEvent event) throws IOException {
    	String url="/view/Accounts.fxml";
		Outil.load(event, url);
    }

    @FXML
    void searchCompte(ActionEvent event) {
    	if(!numerocomptesearch.getText().trim().equals("")) {
        	String sql="Select co.numero,co.solde,co.dateouverture,"
        			+ "co.Agio,co.fraisouverture,co.tauxremuneration,co.etat,co.type,"
        			+ "cli.INE,cli.nomcomplet,cli.tel,cli.profession,cli.adresse,cli.datenaiss,"
        			+ "cli.email,cli.photo,"
        			+ "ag.nom,ag.adresse,"
        			+ "crea.nom,crea.tel,p.libelleP,crea.image,"
        			+ " crea.idE,ag.idA,p.idP "
        			+ " from client cli,agence ag,compte co,poste p,employe crea "
        			+ " where co.numero= '"+numerocomptesearch.getText()+"'"
        			+ " AND co.idClient=cli.idC AND co.idCreateur=crea.idE "
        			+ " AND co.idAgence=ag.idA "
        			+ " AND crea.idPoste=p.idP"
        			+ " limit 1";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			if(rs!=null)
    			{
    				while(rs.next())
    				{	
    					numerocompte.setText(rs.getString(1));
    					solde.setText(rs.getString(2));
    					dateouv.setText(rs.getString(3));
    					agio.setText(rs.getString(4));
    					fraisouv.setText(rs.getString(5));
    					tauxren.setText(rs.getString(6));
    					etat.setText(rs.getString(7));
    					type.setText(rs.getString(8));
    					nomcli.setText(rs.getString(10));
    					phonecli.setText(rs.getString(11));
    					jobcli.setText(rs.getString(12));
    					adresscli.setText(rs.getString(13));
    					borncli.setText(rs.getString(14));
    					mailcli.setText(rs.getString(15));
    					Image pic = new Image(rs.getBinaryStream(16));
    					photocli.setImage(pic);
    					nomagence.setText(rs.getString(17));
    					adresseagence.setText(rs.getString(18));
    					nomcreateur.setText(rs.getString(19));
    					phonecreateur.setText(rs.getString(20));
    					postecrea.setText(rs.getString(21));
    					Image pic1 = new Image(rs.getBinaryStream(22));
    					photocreateur.setImage(pic1);
    					
    					String sql1="Select cons.nom,cons.adresse,cons.domaine"
    							+ " from compte co,employeur cons "
    							+ " where co.numero= '"+numerocomptesearch.getText()+"'"
    							+ " AND co.idConseille=cons.id";
    					
    					try {
							db.initPrepare(sql1);
							rs2=db.executeSELECT();
							if(rs2!=null)
							{
								while(rs2.next())
								{
									nomconseille.setText(rs2.getString(1));
									adresseconseille.setText(rs2.getString(2));
									domaineconseille.setText(rs2.getString(3));
								}
							}
							
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
    					
    				}
    			}
    			else
    			{
    				Outil.showErrorMessage("BANK", "Null");
    			}
    		}
    			
    		 catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
    		Outil.showErrorMessage("BANK", "Veuillez saisir un numero de compte");
        }

    }
    
    
    @FXML
    void showrelevebancaire(ActionEvent event) {
    	if(!numerocomptesearch.getText().trim().equals("")) {
        	String sql="Select * from compte where numero= '"+numerocomptesearch.getText()+"'";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			
    				while(rs.next())
    				{	
    					if(rs.getString("numero").equals(numerocomptesearch.getText()))
    	    			{
    						numSe=numerocomptesearch.getText();
    						String url="/view/ConsultationReleve.fxml";
    						Outil.load(event, url);
    						
    	    			}	
    	    			else
    	    			{
    	    				Outil.showErrorMessage("BANK", "Null");
    	    			}
    				}
    			
    		}
    			
    		 catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
    		Outil.showErrorMessage("BANK", "Veuillez saisir un numero de compte");
        }

    }
    
    @FXML
    void showstatistique(ActionEvent event) {
    	if(!numerocomptesearch.getText().trim().equals("")) {
        	String sql="Select * from compte where numero= '"+numerocomptesearch.getText()+"'";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			
    				while(rs.next())
    				{	
    					if(rs.getString("numero").equals(numerocomptesearch.getText()))
    	    			{
    						numSe=numerocomptesearch.getText();
    						String url="/view/ConsultationStatistique.fxml";
    						Outil.load(event, url);
    						
    	    			}	
    	    			else
    	    			{
    	    				Outil.showErrorMessage("BANK", "Null");
    	    			}
    				}
    			
    		}
    			
    		 catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
    		Outil.showErrorMessage("BANK", "Veuillez saisir un numero de compte");
        }
    }
    
    @FXML
    void imprimerreleve(ActionEvent event) {
    	
    						if(datedep.getValue()==null){	
    							Outil.showErrorMessage("Erreur", "Verifier la Date de debut");
    						}else if(datefin.getValue()==null){	
    							Outil.showErrorMessage("Erreur", "Verifier la Date de Fin");
    							
    						}
    						else {					
    						
    						try {
								JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\HP\\eclipse-workspace\\Bank\\src\\view\\ReleveJavaBank.jrxml");
								JasperReport jReport = JasperCompileManager.compileReport(jDesign);
								
								
								Map parameters = new HashMap();
								parameters.put("numeroParametre",numSe);
								parameters.put("datedepParametre",datedep.getValue().toString());
								parameters.put("datearrParametre",datefin.getValue().toString());
								JasperPrint jPrint = JasperFillManager.fillReport(jReport,parameters,db.Connexion());
								JasperViewer jv =new JasperViewer(jPrint,false);
								jv.viewReport(jPrint,false);
			
								/*JasperCompileManager.compileReportToFile("C:/Users/HP/eclipse-workspace/Bank/src/view/RecuDepotJavaBank.jrxml", "F:/JavaBankPdfFile/RecuDepotJavaBank.pdf");
								try {
									Desktop.getDesktop().open( new File ("F:/JavaBankPdfFile/RecuDepotJavaBank.pdf"));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
								
					    	} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
    						}
    						

    	}

    @FXML
    void imprimerstatistique(ActionEvent event) {
    	
    						if(datedep.getValue()==null){	
    							Outil.showErrorMessage("Erreur", "Verifier la Date de debut");
    						}else if(datefin.getValue()==null){	
    							Outil.showErrorMessage("Erreur", "Verifier la Date de Fin");
    							
    						}
    						else {					
    						
    						try {
								JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\HP\\eclipse-workspace\\Bank\\src\\view\\StatistiqueJavaBank.jrxml");
								JasperReport jReport = JasperCompileManager.compileReport(jDesign);
								
								Map parameters = new HashMap();
								parameters.put("numeroParametre",numSe);
								parameters.put("datedepParametre",datedep.getValue().toString());
								parameters.put("datearrParametre",datefin.getValue().toString());
								JasperPrint jPrint = JasperFillManager.fillReport(jReport,parameters,db.Connexion());
								JasperViewer jv =new JasperViewer(jPrint,false);
								jv.viewReport(jPrint,false);
			
								/*JROdtExporter exporter = new JROdtExporter(); 
								exporter.setParameter(JRExporterParameter.JASPER_PRINT, jPrint); 
								try {
									exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(new File("F:/JavaBankPdfFile/StatistiqueJavaBank.pdf")));
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								exporter .exportReport();
								*/
								/*JasperCompileManager.compileReportToFile("C:/Users/HP/eclipse-workspace/Bank/src/view/StatistiqueJavaBank.jrxml", "F:/JavaBankPdfFile/StatistiqueJavaBank.html");
								try {
									Desktop.getDesktop().open( new File ("F:/JavaBankPdfFile/StatistiqueJavaBank.html"));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
								
					    	} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
    						}
    						

    	}
    
}