package model;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Employe;
import entities.Employeur;
import method.IEmploye;



	public class EmployeDao implements IEmploye{
		private DB db;
		private int ok;
		private ResultSet rs;
		private boolean bol;
		public EmployeDao() {
			db=new DB();
		}
		@Override
		public Employe getLoginUser(String login, String password) {
			Employe em=null;
			String sql="SELECT * FROM employe WHERE nom=? AND password=? AND (idPoste=2 OR idPoste=3)";
			try {
			db.initPrepare(sql);
			db.getPstm().setString(1, login);
			db.getPstm().setString(2, password);
			ResultSet rs=db.executeSELECT();
			while(rs.next()) {
				em=new Employe();
				em.setIdE(rs.getInt(1));
				em.setMatricule(rs.getString(2));
				em.setNom(rs.getString(3));
				em.setTel(rs.getInt(4));
				em.setIdPoste(rs.getInt(5));
				em.setPassword(rs.getString(6));
			}
			rs.close();
			db.closeConnexion();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return em;
		}
		
		@Override
		public Employe getLoginAdmin(String login, String password) {
			Employe em=null;
			String sql="SELECT * FROM employe WHERE nom=? AND password=? AND idPoste=1";
			try {
			db.initPrepare(sql);
			db.getPstm().setString(1, login);
			db.getPstm().setString(2, password);
			ResultSet rs=db.executeSELECT();
			while(rs.next()) {
				em=new Employe();
				em.setIdE(rs.getInt(1));
				em.setMatricule(rs.getString(2));
				em.setNom(rs.getString(3));
				em.setTel(rs.getInt(4));
				em.setIdPoste(rs.getInt(5));
				em.setPassword(rs.getString(6));
			}
			rs.close();
			db.closeConnexion();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return em;
		}
		
		@Override
		public List<Employe> liste() {
			List<Employe> l_employe=new ArrayList<Employe>();
			String sql="SELECT * FROM employe";
			try {
				db.initPrepare(sql);
				rs=db.executeSELECT();
				
				while(rs.next()) {
					Employe em=new Employe();
					em.setIdE(rs.getInt(1));
					em.setMatricule(rs.getString(2));
					em.setNom(rs.getString(3));
					em.setTel(rs.getInt(4));
					em.setIdPoste(rs.getInt(5));
					em.setPassword(rs.getString(6));
					l_employe.add(em);	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return l_employe;
		}
		@Override
		public int add(Employe em) {
			String sql="INSERT INTO employe VALUES(null,?,?,?,?,?,?,?)";
			
			try {
				db.initPrepare(sql);
				db.getPstm().setString(1, em.getMatricule());
				db.getPstm().setString(2, em.getNom());
				db.getPstm().setInt(3, em.getTel());
				db.getPstm().setInt(4, em.getIdPoste());
				db.getPstm().setInt(5, em.getIdAgence());
				db.getPstm().setString(6, em.getPassword());
				db.getPstm().setBinaryStream(7, em.getPhoto());
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
		public boolean delete(int id) {
			String sql="DELETE FROM employe WHERE idE=?";
			try {
				db.initPrepare(sql);
				db.getPstm().setInt(1, id);
				ok=db.executeMAJ();
				if(ok!=0) {
					bol=true;
				}
				
			} catch (Exception e) {
				bol=false;
				e.printStackTrace();
			}
			
			return bol;
		}
		@Override
		public boolean update(Employe em) {
			String sql="UPDATE employe SET matricule=?,nom=?,tel=?,idPoste=?,password=? WHERE idE=?";
			bol=false;
			try {
				db.initPrepare(sql);
				db.getPstm().setString(1, em.getMatricule());
				db.getPstm().setString(2, em.getNom());
				db.getPstm().setInt(3, em.getTel());
				db.getPstm().setInt(4, em.getIdPoste());
				db.getPstm().setString(5, em.getPassword());
				ok=db.executeMAJ();
				if(ok!=0) {
					bol=true;
				}
				
			} catch (Exception e) {
				bol=false;
				e.printStackTrace();
			}
			
			return bol;
		}
		@Override
		public Employe getEmployeById(int id) {
			Employe em=null;
			String sql="SELECT * FROM employe WHERE idE=?";
			try {
				db.initPrepare(sql);
				db.executeSELECT();
				while(rs.next()) {
					em=new Employe();
					em.setIdE(rs.getInt(1));
					em.setMatricule(rs.getString(2));
					em.setNom(rs.getString(3));
					em.setTel(rs.getInt(4));
					em.setIdPoste(rs.getInt(5));
					em.setPassword(rs.getString(6));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return em;
		}
		
		@Override
		public ResultSet cbgetidiemp(String nomint,String table,String id,String nom)
		{
			
			String sql="SELECT "+id+" FROM "+table+" WHERE "+nomint+" = '"+nom+"'";
			db.initPrepare(sql);
			rs=db.executeSELECT();
			if(rs!=null) {
			return rs;		
			}
			return null;
		}
		@Override
		public int addEmployeur(Employeur u) {
			String sql="INSERT INTO employeur VALUES(null,?,?,?)";
			
			try {
				db.initPrepare(sql);
				db.getPstm().setString(1, u.getNomEmp());
				db.getPstm().setString(2, u.getAdr());
				db.getPstm().setString(3, u.getDomaine());
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
		

	}

	

