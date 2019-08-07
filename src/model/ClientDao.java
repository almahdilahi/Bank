package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Client;
import entities.Employe;
import javafx.scene.image.Image;
import method.IClient;

public class ClientDao implements IClient {
	private DB db;
	private int ok;
	private ResultSet rs;
	private boolean bol;
	public ClientDao() {
		db=new DB();
	}
	@Override
	public List<Client> liste() {
		List<Client> l_client=new ArrayList<Client>();
		String sql="SELECT * FROM client";
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			
			while(rs.next()) {
				Client cl=new Client();
				cl.setIdC(rs.getInt(1));
				cl.setINE(rs.getInt(2));
				cl.setNomcomplet(rs.getString(3));
				cl.setTel(rs.getInt(4));
				cl.setProfession(rs.getString(5));
				cl.setAdresse(rs.getString(6));
				cl.setDatenaiss((LocalDate) rs.getObject(7));
				cl.setEmail(rs.getString(8));
				cl.setPhoto((InputStream) rs.getObject(9));
				l_client.add(cl);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l_client;
		
	}

	@Override
	public int add(Client cl) {
		
		String sql="INSERT INTO client VALUES(null,?,?,?,?,?,?,?,?)";
		
		try {
			db.initPrepare(sql);
			db.getPstm().setInt(1, cl.getINE());
			db.getPstm().setString(2, cl.getNomcomplet());
			db.getPstm().setInt(3, cl.getTel());
			db.getPstm().setString(4, cl.getProfession());
			db.getPstm().setString(5, cl.getAdresse());
			db.getPstm().setObject(6, cl.getDatenaiss());
			db.getPstm().setString(7, cl.getEmail());
			db.getPstm().setBinaryStream(8, cl.getPhoto());
			//db.getPstm().setInt(9, cl.getIdEmp());
			ok=db.executeMAJ();
			rs=db.getPstm().getGeneratedKeys();
			while(rs.next()){
				ok=rs.getInt(1);
			}
			rs.close();
			db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}

	@Override
	public boolean update(Client c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client getClientById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Client getLoginClient(String login, String password) {
		Client cli=null;
		String sql="SELECT * FROM client WHERE nomcomplet=? AND INE=?";
		try {
		db.initPrepare(sql);
		db.getPstm().setString(1, login);
		db.getPstm().setString(2, password);
		ResultSet rs=db.executeSELECT();
		while(rs.next()) {
			cli=new Client();
			cli.setIdC(rs.getInt(1));
			cli.setINE(rs.getInt(2));
			cli.setNomcomplet(rs.getString(3));
			cli.setTel(rs.getInt(4));
			cli.setProfession(rs.getString(5));
		}
		rs.close();
		db.closeConnexion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cli;
		
	}
	

}
