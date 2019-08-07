package model;

import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Client;
import entities.Compte;
import entities.Courant;
import entities.Simple;
import entities.Teranga;
import method.ICompte;

public class CompteDao implements ICompte {
	private DB db;
	private int ok;
	private ResultSet rs;
	private boolean bol;
	public  CompteDao() {
		db=new DB();
	}
	@Override
	public List<Compte> liste() {
		List<Compte> l_compte=new ArrayList<Compte>();
		String sql="SELECT * FROM compte";
		try {
			db.initPrepare(sql);
			rs=db.executeSELECT();
			
			while(rs.next()) {
				
				Compte c = new Compte() {
				};
				
				c.setIdCompte(rs.getInt(1));
				c.setNumero(rs.getString(2));
				c.setSolde(rs.getInt(3));
				c.setDtouv(rs.getDate(4));
				c.setIdClient(rs.getInt(5));
				c.setIdCreateur(rs.getInt(6));
				c.setIdConseille(rs.getInt(7));
				c.setIdAgence(rs.getInt(8));
				c.setAgio(rs.getInt(9));
				c.setFraisouv(rs.getInt(10));
				c.setTauxren(rs.getFloat(11));
				c.setEtat(rs.getString(12));
				c.setType(rs.getString(13));
				
				l_compte.add(c);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l_compte;
	}

	@Override
	public int add(Compte cl,String type) {
		
		String sql="INSERT INTO compte VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		Courant cr = new Courant();
		Simple s = new Simple();
		Teranga t = new Teranga();
		try {
			db.initPrepare(sql);
			if(type.equals("Courant"))
			{
				db.getPstm().setString(1, cl.getNumero());
				db.getPstm().setInt(2, cl.getSolde());
				db.getPstm().setObject(3, cl.getDateouverture());
				db.getPstm().setInt(4, cl.getIdClient());
				db.getPstm().setInt(5, cl.getIdCreateur());
				db.getPstm().setInt(6, cl.getIdConseille());
				db.getPstm().setInt(7, cl.getIdAgence());
				db.getPstm().setInt(8, cr.getAgio());
				db.getPstm().setObject(9,null);
				db.getPstm().setFloat(10, cr.getTauxRem());
				db.getPstm().setString(11, cl.getEtat());
				db.getPstm().setString(12, "Courant");
			}
			else if(type.equals("Teranga"))
			{
				db.getPstm().setString(1, cl.getNumero());
				db.getPstm().setInt(2, cl.getSolde());
				db.getPstm().setObject(3, cl.getDateouverture());
				db.getPstm().setInt(4, cl.getIdClient());
				db.getPstm().setInt(5, cl.getIdCreateur());
				db.getPstm().setObject(6, null);	
				db.getPstm().setInt(7, cl.getIdAgence());				
				db.getPstm().setObject(8, null);
				db.getPstm().setInt(9,t.getFraisOuv());
				db.getPstm().setFloat(10, t.getTauxRem());
				db.getPstm().setString(11, cl.getEtat());
				db.getPstm().setString(12, "Teranga");
			}else 
			{
				db.getPstm().setString(1, cl.getNumero());
				db.getPstm().setInt(2, cl.getSolde());
				db.getPstm().setObject(3, cl.getDateouverture());
				db.getPstm().setInt(4, cl.getIdClient());
				db.getPstm().setInt(5, cl.getIdCreateur());
				db.getPstm().setObject(6, null);	
				db.getPstm().setInt(7, cl.getIdAgence());	
				db.getPstm().setObject(8, null);
				db.getPstm().setInt(9,s.getFraisOuv());
				db.getPstm().setFloat(10, s.getTauxRem());
				db.getPstm().setString(11, cl.getEtat());
				db.getPstm().setString(12, "Simple");
			}
			
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
	public boolean updatesoldeadd(int montant,int id) {
		String sql="UPDATE compte SET solde=solde+? WHERE idCompte=?";
		bol=false;
		try {
			db.initPrepare(sql);
			db.getPstm().setInt(1, montant);
			db.getPstm().setInt(2, id);
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
	public boolean updatesoldedel(int montant,int id) {
		String sql="UPDATE compte SET solde=solde-? WHERE idCompte=?";
		bol=false;
		try {
			db.initPrepare(sql);
			db.getPstm().setInt(1, montant);
			db.getPstm().setInt(2, id);
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
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Compte getCompteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet cbgetid(String nomint,String table,String id,String nom)
	{
		
		String sql="SELECT "+id+" FROM "+table+" WHERE "+nomint+" = '"+nom+"'";
		db.initPrepare(sql);
		rs=db.executeSELECT();
		if(rs!=null) {
		return rs;		
		}
		return null;
	}

}
