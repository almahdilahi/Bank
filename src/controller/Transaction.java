package controller;

import java.awt.Desktop;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

import entities.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import method.ICompte;
import method.ITransaction;
import method.Outil;
import model.CompteDao;
import model.DB;
import model.TransactionDao;
	import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.logging.*;
import org.springframework.core.*;
import org.springframework.asm.*;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.beans.*;
import org.apache.batik.bridge.*;
import org.apache.xmlgraphics.*;
import org.w3c.dom.svg.*;
import org.w3c.css.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.lowagie.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
@SuppressWarnings("unused")
public class Transaction implements Initializable{

    @FXML
    private TextField idcomptedepot_txt;

    @FXML
    private TextField montantdepot_txt;

    @FXML
    private DatePicker datedepot_txt;

    @FXML
    private Button btn_savedepot;

    @FXML
    private ComboBox<String> cbagencedepot;

    @FXML
    private ComboBox<String> cbcaissierdepot;

    @FXML
    private TextField idcompteretrait_txt;

    @FXML
    private TextField montantretrait_txt;

    @FXML
    private DatePicker dateretrait_txt;

    @FXML
    private Button btn_saveretrait;

    @FXML
    private ComboBox<String> cbagenceretrait;

    @FXML
    private ComboBox<String> cbcaissierretrait;

    @FXML
    private TextField idcomptevirement1_txt;

    @FXML
    private TextField montantvirement1_txt;

    @FXML
    private TextField idcomptevirement2_txt1;

    @FXML
    private Button btn_savevirement;
   	private ITransaction it;
 	private ResultSet rs;
 	private ResultSet rs1;
 	private ResultSet rsAg;
 	private ResultSet rsCais;
 	private DB db;
 	private ICompte ic;
 	private String Agencevirement;
    
 	public Transaction() {
 		db=new DB();
 	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	cbcaissierdepot.setValue(entities.Operation.paramsCaissier);
    	cbcaissierretrait.setValue(entities.Operation.paramsCaissier);
    	datedepot_txt.setValue(LocalDate.now());
    	dateretrait_txt.setValue(LocalDate.now());
    	//fillinComboBoxAgence();
    	//fillinComboBoxEmploye();
    	/*
    	try {
			JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\HP\\eclipse-workspace\\Bank\\src\\view\\RecuDepotJavaBank.jrxml");
			JasperReport jReport = JasperCompileManager.compileReport(jDesign);

			Map parameters = new HashMap();
			parameters.put("monParametre",1);

		
			JasperPrint jPrint = JasperFillManager.fillReport(jReport,parameters,db.Connexion());
			JasperViewer.viewReport(jPrint,true);
			
			 try {
				JasperExportManager.exportReportToPdfFile(jPrint,"F:/JavaBankPdfFile/RecuDepotJavaBank.pdf");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			 
			 /*JasperCompileManager.compileReportToFile("C:/Users/HP/eclipse-workspace/Bank/src/view/RecuDepotJavaBank.jrxml", "F:/JavaBankPdfFile/RecuDepotJavaBank.pdf");
			*/
    	/*try {
				Desktop.getDesktop().open( new File ("F:/JavaBankPdfFile/RecuDepotJavaBank.pdf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    	}	 catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    		    
	}
    
    

    @FXML
    void depot(ActionEvent event) throws PrintException, IOException{
    	
    	//String idC=idcomptedepot_txt.getText();
    	String montdep=montantdepot_txt.getText();
    	//LocalDate datdep=datedepot_txt.getValue();
    	int idAgence = 0;
		int idCaissier = 0;
		int idC=0; 
		   	
	//	System.out.println(cbcaissierdepot.getValue());	
	/*	try {
			rsAg=it.cbgetid("nom", "agence", "idA", cbagencedepot.getValue());
			while(rsAg.next()) {
			idAgence=rsAg.getInt("idA");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			rsCais = it.cbgetid("nom", "employe", "idE", cbcaissierdepot.getValue());
			while(rsCais.next()) {
			idCaissier=rsCais.getInt("idE");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		String sqlcomdep="select idCompte from compte where numero = '"+idcomptedepot_txt.getText()+"'";
		try {
			db.initPrepare(sqlcomdep);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idC=rs.getInt("idCompte");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		String sqlagdep="select idA from agence where nom = '"+ cbagencedepot.getValue()+"'";
		try {
			db.initPrepare(sqlagdep);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idAgence=rs.getInt("idA");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sqlcais="select idE from employe where nom = '"+ cbcaissierdepot.getValue()+"'";
		try {
			db.initPrepare(sqlcais);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idCaissier=rs.getInt("idE");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
    	
		
		if(idcomptedepot_txt.getText().trim().equals("")) {
			Outil.showErrorMessage("@2LK", "Verifier le numero de compte");
		}
		
			
		else if(!isANumber(montdep)  || Integer.parseInt(montdep)<200) {
						Outil.showErrorMessage("@2LK", "Verifier le montant au moins egal a 200");
					}
					else if(datedepot_txt.getValue()==null){
						Outil.showErrorMessage("@2LK", "Verifier lA Date");	
						
					}
					else if(cbagencedepot.getValue()==null) {
						Outil.showErrorMessage("@2LK", "Verifier Agence");
					}
					else {
						
										
						it = new TransactionDao();
						Operation op = new Operation();	
						op.setIdCompte(idC);
						op.setMontant(Integer.parseInt(montdep));
						op.setDateop(LocalDate.now());
						op.setIdAgence(idAgence);
						op.setIdCaissier(idCaissier);
						int t=it.adddepot(op);
						if(t != 0) {
							Outil.showConfirmationMessage("@2LK", "Depot Effectue");
							//System.out.println(op.getMontant()+"--"+op.getIdCompte());
							ic = new CompteDao();
							try {
								//System.out.println(ic);
								ic.updatesoldeadd(op.getMontant(),op.getIdCompte());
								Outil.showConfirmationMessage("@2LK", "Account N "+op.getIdCompte()+" updated");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
								Outil.showErrorMessage("BANK", "Le compte n'a pas ete mise a jour");
							}
							
							
						
					    	try {
								JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\HP\\eclipse-workspace\\Bank\\src\\view\\RecuDepotJavaBank.jrxml");
								JasperReport jReport = JasperCompileManager.compileReport(jDesign);

								Map parameters = new HashMap();
								parameters.put("monParametre",t);

							
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
						    
							 
						        
						}else {
							Outil.showErrorMessage("@2LK", "Data not Added");
						}
					}
					
				
	
		
			
    }
		
		
		
    


    @FXML
    void retrait(ActionEvent event) throws IOException{
    	//String idC=idcompteretrait_txt.getText();
    	String montdep=montantretrait_txt.getText();
    	//LocalDate datdep=dateretrait_txt.getValue();
    	int idAgence = 0;
		int idCaissier = 0;
		int idC = 0;
		int montantinitial = 0;
	//	System.out.println(cbcaissierdepot.getValue());	
	/*	try {
			rsAg=it.cbgetid("nom", "agence", "idA", cbagencedepot.getValue());
			while(rsAg.next()) {
			idAgence=rsAg.getInt("idA");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			rsCais = it.cbgetid("nom", "employe", "idE", cbcaissierdepot.getValue());
			while(rsCais.next()) {
			idCaissier=rsCais.getInt("idE");
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		

			String sqlcomret="select idCompte from compte where numero = '"+idcompteretrait_txt.getText()+"'";
			try {
				db.initPrepare(sqlcomret);
				rs=db.executeSELECT();
				if(rs!=null) {
					while(rs.next()) {
						idC=rs.getInt("idCompte");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String sqlcomret1="select solde from compte where numero = '"+idcompteretrait_txt.getText()+"'";
			try {
				db.initPrepare(sqlcomret1);
				rs=db.executeSELECT();
				if(rs!=null) {
					while(rs.next()) {
						montantinitial=rs.getInt("solde");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		 
		String sqlagret="select idA from agence where nom = '"+ cbagenceretrait.getValue()+"'";
		try {
			db.initPrepare(sqlagret);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idAgence=rs.getInt("idA");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sqlcais="select idE from employe where nom = '"+ cbcaissierretrait.getValue()+"'";
		try {
			db.initPrepare(sqlcais);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idCaissier=rs.getInt("idE");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		if(idcompteretrait_txt.getText().trim().equals("")) {
		
			Outil.showErrorMessage("@2LK", "Verifier le numero de compte");
		}
		
		
		else if(!isANumber(montdep)  || Integer.parseInt(montdep)<200) {
						Outil.showErrorMessage("@2LK", "Verifier le montant au moins egal a 200");
					}
					else if(dateretrait_txt.getValue()==null){
						Outil.showErrorMessage("@2LK", "Verifier lA Date");	
						
					}
					else if(cbagenceretrait.getValue()==null) {
						Outil.showErrorMessage("@2LK", "Verifier Agence");
					}
					else {
						
						if(montantinitial >= Integer.parseInt(montdep)) {
							if((montantinitial - Integer.parseInt(montdep)) < 1000 ) {
								Outil.showErrorMessage("@2LK", "Retrait Impossible Votre Compte doit rester au moins 1000 CFA");
							}else {								
						
						it = new TransactionDao();
						Operation op = new Operation();
						
						op.setIdCompte(idC);
						op.setMontant(Integer.parseInt(montdep));
						op.setDateop(LocalDate.now());
						op.setIdAgence(idAgence);
						op.setIdCaissier(idCaissier);
						int t=it.addretrait(op);
						if(t != 0) {
							Outil.showConfirmationMessage("@2LK", "Retrait Effectue");
							ic = new CompteDao();
							cleandepot();
							try {		
								ic.updatesoldedel(op.getMontant(),op.getIdCompte());
								Outil.showConfirmationMessage("@2LK", "Account N "+op.getIdCompte()+" updated");
							} catch (Exception e) {
								Outil.showErrorMessage("BANK", "Le compte n'a pas ete mise a jour");
							
							}
							//ctrl+shift+o
							/*InputStream arq = Transaction.class.getResourceAsStream("/view/recudepot.jrxml");
							JasperReport report = JasperCompileManager.compileReport(arq);
							JasperPrint print = JasperFillManager.fillReport(report, null,new JRBeanCollectionDataSource(it.liste(t)));
							JasperViewer.viewReport(print,false);*/
							//Desktop.getDesktop().open( new File ("F:/RecuRetraitJavaBank.pdf"));
						
					    	try {
								JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\HP\\eclipse-workspace\\Bank\\src\\view\\RecuRetraitJavaBank.jrxml");
								JasperReport jReport = JasperCompileManager.compileReport(jDesign);

								Map parameters = new HashMap();
								parameters.put("monParametre",t);

								// - Execution du rapport
								JRDataSource jrDataSource = new JRDataSource() {
									
									@Override
									public boolean next() throws JRException {
										// TODO Auto-generated method stub
										return false;
									}
									
									@Override
									public Object getFieldValue(JRField arg0) throws JRException {
										// TODO Auto-generated method stub
										return null;
									}
								};
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
						
						}else {
							Outil.showErrorMessage("@2LK", "Data not Added");
						}
						
						}
					
						}else {
						Outil.showErrorMessage("@2LK", "Retrait Impossible: Le montant que vous voulez retirer est superieur au montant du compte");
					}
						
					}
					
			
				
    }

    @FXML
    void virement(ActionEvent event) throws IOException  {
    	
    	String montdep = montantvirement1_txt.getText();
    	
    	int idAgence = 0;
    	int idCaissier = 0;
    	int idC = 0;
    	int montantinitial = 0;
    	int idCdebit = 0;
    	
    	String sqlcom="select idCompte from compte where numero = '"+idcomptevirement2_txt1.getText()+"'";
		try {
			db.initPrepare(sqlcom);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idC=rs.getInt("idCompte");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
		String sqlcom1="select idCompte from compte where numero = '"+idcomptevirement1_txt.getText()+"'";
		try {
			db.initPrepare(sqlcom1);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idCdebit=rs.getInt("idCompte");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
		String sqlcomvir1="select solde from compte where numero = '"+idcomptevirement1_txt.getText()+"'";
		try {
			db.initPrepare(sqlcomvir1);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					montantinitial=rs.getInt("solde");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    	String sqlag="select idA from agence where nom = '"+ Agencevirement+"'";
		try {
			db.initPrepare(sqlag);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idAgence=rs.getInt("idA");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sqlcais="select idE from employe where nom = '"+ cbcaissierretrait.getValue()+"'";
		try {
			db.initPrepare(sqlcais);
			rs=db.executeSELECT();
			if(rs!=null) {
				while(rs.next()) {
					idCaissier=rs.getInt("idE");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    	
    	if(idcomptevirement1_txt.getText().trim().equals("")) {
    		
			Outil.showErrorMessage("@2LK", "Verifier le numero de compte donnant");
		}
    	
    	else if(idcomptevirement2_txt1.getText().trim().equals("")) {
    		
			Outil.showErrorMessage("@2LK", "Verifier le numero de compte recevant");
		 }
			
		
		else if(!isANumber(montdep)  || Integer.parseInt(montdep)<200) {
						Outil.showErrorMessage("@2LK", "Verifier le montant au moins egal a 200");
		 }
    	
		else {
			
			if(montantinitial > Integer.parseInt(montdep)) {
				
				if((montantinitial - Integer.parseInt(montdep)) < 1000 ) {
					Outil.showErrorMessage("@2LK", "Virement Impossible Votre Compte doit rester au moins 1000 CFA");
				}
				else if(idcomptevirement1_txt.getText().trim().equals(idcomptevirement2_txt1.getText().trim())) {
					Outil.showErrorMessage("@2LK", "Virement Impossible Les deux comptes sont identiques");	
				}
				else {
				it = new TransactionDao();
				Operation op = new Operation();
				
				op.setIdCompte(idC);
				op.setMontant(Integer.parseInt(montdep));
				op.setDateop(LocalDate.now());
				op.setIdAgence(idAgence);
				op.setIdCaissier(idCaissier);
				int t=it.addvirement(op);
				if(t != 0) {
					Outil.showConfirmationMessage("@2LK", "Virement Effectue");
					
					
					ic = new CompteDao();
					try {
						ic.updatesoldeadd(op.getMontant(),op.getIdCompte());
						Outil.showConfirmationMessage("@2LK", "Recevant: Le Compte N "+op.getIdCompte()+" a ete crediter avec succes");
						ic.updatesoldedel(op.getMontant(),idCdebit);
						Outil.showConfirmationMessage("@2LK", "Donnant: Le Compte N "+idCdebit+" a ete debiter avec succes");
					} catch (Exception e) {
						Outil.showErrorMessage("BANK", "Le compte n'a pas ete mise a jour");
					
					}
					//ctrl+shift+o
				/*	InputStream arq = Transaction.class.getResourceAsStream("/view/recudepot.jrxml");
					JasperReport report = JasperCompileManager.compileReport(arq);
					JasperPrint print = JasperFillManager.fillReport(report, null,new JRBeanCollectionDataSource(it.liste(t)));
					JasperViewer.viewReport(print,false);*/
					//Desktop.getDesktop().open( new File ("F:/RecuVirementJavaBank.pdf"));
			    	try {
						JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\HP\\eclipse-workspace\\Bank\\src\\view\\RecuVirementJavaBank.jrxml");
						JasperReport jReport = JasperCompileManager.compileReport(jDesign);

						Map parameters = new HashMap();
						parameters.put("monParametre",t);

						// - Execution du rapport
						JRDataSource jrDataSource = new JRDataSource() {
							
							@Override
							public boolean next() throws JRException {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public Object getFieldValue(JRField arg0) throws JRException {
								// TODO Auto-generated method stub
								return null;
							}
						};
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
				
				}else {
					Outil.showErrorMessage("@2LK", "Data not Added");
				}
			}
			
			}else {
				Outil.showErrorMessage("@2LK", "Virement Impossible: Le montant que vous voulez virer est superieur au montant du compte");
			}
			
			}
				
			
				 	
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
    	String url="/view/Login.fxml";
		Outil.load(event, url);
    }
    @FXML
    void back(ActionEvent event) throws IOException {
    	String url="/view/AccueilClient.fxml";
		Outil.load(event, url);
    }
    
    @FXML
    void searchNumeroCompteDepot(ActionEvent event) {
    	if(!idcomptedepot_txt.getText().trim().equals("")) {
        	String sql="Select * from compte where numero= '"+idcomptedepot_txt.getText()+"'";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			//System.out.println(rs.first());
    			if(rs != null)
    			{
    				Outil.showConfirmationMessage("BANK", "Account Number Exist");
    				while(rs.next())
    				{	
    					String sql2="Select * from agence where idA= '"+rs.getString("idAgence")+"'";
    		        	
    		    		try {
    		    			db.initPrepare(sql2);
    		    			rs1=db.executeSELECT();
    		    			if(rs1!=null)
    		    			{
    		    				while(rs1.next())
    		    				{	
    		    					datedepot_txt.setValue(LocalDate.now());
    		    					cbagencedepot.setValue(rs1.getString("nom"));
    		    					
    		    				}
    		    			}
    		    			
    		    		}
    		    			
    		    		 catch (Exception e) {
    		    			e.printStackTrace();
    		    		}
    					
    					
    				}
    			}
    			else
    			{
    				Outil.showErrorMessage("BANK", "Account Number Failed");
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
    void searchNumeroCompteRetrait(ActionEvent event) {
    	if(!idcompteretrait_txt.getText().trim().equals("")) {
        	String sql="Select * from compte where numero= '"+idcompteretrait_txt.getText()+"'";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			if(rs!=null)
    			{
    				Outil.showConfirmationMessage("BANK", "Account Number Exist");
    				while(rs.next())
    				{	
    					String sql2="Select * from agence where idA= '"+rs.getString("idAgence")+"'";
    		        	
    		    		try {
    		    			db.initPrepare(sql2);
    		    			rs1=db.executeSELECT();
    		    			if(rs1!=null)
    		    			{
    		    				while(rs1.next())
    		    				{	
    		    			    	dateretrait_txt.setValue(LocalDate.now());
    		    					cbagenceretrait.setValue(rs1.getString("nom"));
    		    					
    		    				}
    		    			}
    		    			
    		    		}
    		    			
    		    		 catch (Exception e) {
    		    			e.printStackTrace();
    		    		}
    					
    					
    				}
    			}
    			else
    			{
    				Outil.showErrorMessage("BANK", "Account Number Failed");
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
    void searchNumeroCompteV1(ActionEvent event) {
    	if(!idcomptevirement1_txt.getText().trim().equals("")) {
        	String sql="Select * from compte where numero= '"+idcomptevirement1_txt.getText()+"'";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			if(rs!=null)
    			{
    				Outil.showConfirmationMessage("BANK", "Account Number Exist");
    			}
    			else
    			{
    				Outil.showErrorMessage("BANK", "Account Number Failed");
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
    void searchNumeroCompteV2(ActionEvent event) {
    	if(!idcomptevirement2_txt1.getText().trim().equals("")) {
        	String sql="Select * from compte where numero= '"+idcomptevirement2_txt1.getText()+"'";
        	
    		try {
    			db.initPrepare(sql);
    			rs=db.executeSELECT();
    			if(rs!=null)
    			{
    				Outil.showConfirmationMessage("BANK", "Account Number Exist");
    				while(rs.next())
    				{	
    					String sql2="Select * from agence where idA= '"+rs.getString("idAgence")+"'";
    		        	
    		    		try {
    		    			db.initPrepare(sql2);
    		    			rs1=db.executeSELECT();
    		    			if(rs1!=null)
    		    			{
    		    				while(rs1.next())
    		    				{	
    		    					
    		    					//cbagencedepot.setValue(rs1.getString("nom"));
    		    					Agencevirement = rs1.getString("nom");
    		    					
    		    				}
    		    			}
    		    			
    		    		}
    		    			
    		    		 catch (Exception e) {
    		    			e.printStackTrace();
    		    		}
    					
    					
    				}
    			}
    			else
    			{
    				Outil.showErrorMessage("BANK", "Account Number Failed");
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
    void searchNumeroCompte(ActionEvent event) {

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
    
    
    public void cleandepot() {
    	idcomptedepot_txt.setText("");
    	montantdepot_txt.setText("");
    	datedepot_txt.setPromptText("");
    	cbagencedepot.setPromptText("");
    	
    	idcomptedepot_txt.setPromptText("Numero Compte");
    	montantdepot_txt.setPromptText("Montant");
    	datedepot_txt.setPromptText("Date");
    	cbagencedepot.setPromptText("Agence");
    }
    
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
			cbcaissierdepot.setItems(l_emp);
			cbcaissierretrait.setItems(l_emp);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }
 	
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
			cbagencedepot.setItems(l_ag);
			cbagenceretrait.setItems(l_ag);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
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
    
   /*
      		JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\HP\\eclipse-workspace\\Bank\\src\\view\\RecuDepotJavaBank.jrxml");
			JasperReport jReport = JasperCompileManager.compileReport(jDesign);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("monParametre",new String("1"));
				
			JasperPrint jPrint = JasperFillManager.fillReport(jReport,parameters,db.Connexion());
			// PDF Exportor.
	        JRPdfExporter exporter = new JRPdfExporter();
	 
	        ExporterInput exporterInput = new SimpleExporterInput(jPrint);
	        // ExporterInput
	        exporter.setExporterInput(exporterInput);
	 
	        // ExporterOutput
	        OutputStreamExporterOutput  exporterOutput = new SimpleOutputStreamExporterOutput("F:/JavaBankPdfFile/RecuDepotJavaBank.pdf");
	        // Output
	        exporter.setExporterOutput(exporterOutput);
	 
	        //
	        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
	        exporter.setConfiguration(configuration);
	        exporter.exportReport();	
			//JasperViewer.viewReport(jPrint,true);
			/*JasperCompileManager.compileReportToFile("C:/Users/HP/eclipse-workspace/Bank/src/view/RecuDepotJavaBank.jrxml", "F:/JavaBankPdfFile/RecuDepotJavaBank.pdf");
			try {
				Desktop.getDesktop().open( new File ("F:/JavaBankPdfFile/RecuDepotJavaBank.pdf"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
    
  //ctrl+shift+o
	/*InputStream arq = Transaction.class.getResourceAsStream("/view/recudepot.jrxml");
	JasperReport report = JasperCompileManager.compileReport(arq);
	JRDataSource jrDataSource = new JREmptyDataSource();
	JasperPrint print = JasperFillManager.fillReport(report, null,jrDataSource);
	JasperViewer.viewReport(print,false);
	JasperExportManager.exportReportToPdfFile(print,"C:\\recudepot.pdf");*/
	/*Map<String, Object> parametres = new HashMap<String, Object>(0);
	parametres.put("numero","0001");
	//JRDataSource jrDataSource = new JREmptyDataSource(); 
	InputStream resourceUrl = ClassLoader.getSystemResourceAsStream("/MyReports/MyRecuDepot.jrxml");
	//String url="C:\\Users\\HP\\JaspersoftWorkspace\\MyReports\\MyRecuDepot.jrxml";
	System.out.println(resourceUrl);
	//JasperPrint jp = JasperFillManager.fillReport(ClassLoader.getSystemResourceAsStream("C:\\Users\\HP\\JaspersoftWorkspace\\MyReports\\MyRecuDepot.jrxml"),parametres);
	JasperRunManager.runReportToPdf(resourceUrl, parametres);
	//JasperExportManager.exportReportToPdfFile(jp, "C:\\\\Users\\\\HP\\\\JaspersoftWorkspace\\\\MyReports\\\\MyRecuDepot.pdf");
*/
	

	/*FileInputStream fis = new FileInputStream("F:/RecuDepotJavaBank.pdf");
	DocFlavor docType = DocFlavor.INPUT_STREAM.AUTOSENSE;

	Doc pdfDoc = new SimpleDoc(fis, docType, null);
	DocPrintJob printJob = PrinterJob.getPrinterJob().getPrintService().createPrintJob();
	printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
	fis.close();*/
	
	  /*PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
      DocPrintJob printerJob = defaultPrintService.createPrintJob();
      File pdfFile = new File("F:/RecuDepotJavaBank.pdf");
      SimpleDoc simpleDoc = new SimpleDoc(pdfFile.toURL(), DocFlavor.URL.AUTOSENSE, null);
      printerJob.print(simpleDoc, null);*/
	

	 /*PdfDocument d = new PdfDocument();    
	 
        // Load a PDF document
        d.load("F:/RecuDepotJavaBank.pdf");
      
        // Create a PDF printer object
        PdfPrinter printer = new PdfPrinter();
        printer.setDocument(d);*/
	/*Document d = new Document();
	PdfWriter.getInstance(d, new FileOutputStream("F:/RecuDepotJavaBank.pdf"));
	File pdfFile = new File("F:/RecuDepotJavaBank.pdf");						
	d.open();*/
	
	//Desktop.getDesktop().open( new File ("F:/RecuDepotJavaBank.pdf"));
			
    	
   
 	
 
}