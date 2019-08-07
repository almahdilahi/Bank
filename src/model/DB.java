package model;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {
	
	//Pour l'ouverture de la connexion a la base de donnees 
	private Connection conn;
	//Pour la recuperation des requetes de type SELECT
	private ResultSet rs;
	//Pour l'execution des requetes preparees
	private PreparedStatement pstm;
	//Pour la recuperation des requetes de type INSERT, UPDATE, et DELETE
	private int ok;
	
	public void getConnexion() {
		String url="jdbc:mysql://localhost:3306/javabank";
		String user="root";
		String password="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Connection Connexion() {
		String url="jdbc:mysql://localhost:3306/javabank";
		String user="root";
		String password="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void initPrepare(String sql) {
		getConnexion();
		try {
			if(sql.toLowerCase().startsWith("insert")) {
				pstm=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			else {
				pstm=conn.prepareStatement(sql);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int executeMAJ() {
		try {
			ok=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	public ResultSet executeSELECT() {
        try {
			rs=pstm.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public PreparedStatement getPstm() {
		return this.pstm;
	}
	
	public void closeConnexion() {
		try {
			if(conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
