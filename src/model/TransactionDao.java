package model;

import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.Client;
import entities.Operation;
import method.ITransaction;

public class TransactionDao implements ITransaction{
	private DB db;
	private int ok;
	private ResultSet rs;
	private boolean bol;
	
	public TransactionDao() {
	db=new DB();
	}
	
	@Override
	public int adddepot(Operation op) {
		String sql="INSERT INTO operation VALUES(null,?,?,?,?,?,?)";
		
		try {
			db.initPrepare(sql);
			db.getPstm().setObject(1, op.getDateop());
			db.getPstm().setInt(2, op.getMontant());
			db.getPstm().setString(3, "Depot");
			db.getPstm().setInt(4, op.getIdCompte());
			db.getPstm().setInt(5, op.getIdCaissier());
			db.getPstm().setInt(6, op.getIdAgence());
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
	public int addretrait(Operation op) {
		String sql="INSERT INTO operation VALUES(null,?,?,?,?,?,?)";
		
		try {
			db.initPrepare(sql);
			db.getPstm().setObject(1, op.getDateop());
			db.getPstm().setInt(2, op.getMontant());
			db.getPstm().setString(3, "Retrait");
			db.getPstm().setInt(4, op.getIdCompte());
			db.getPstm().setInt(5, op.getIdCaissier());
			db.getPstm().setInt(6, op.getIdAgence());
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
	public int addvirement(Operation op) {
		String sql="INSERT INTO operation VALUES(null,?,?,?,?,?,?)";
		
		try {
			db.initPrepare(sql);
			db.getPstm().setObject(1, op.getDateop());
			db.getPstm().setInt(2, op.getMontant());
			db.getPstm().setString(3, "Virement");
			db.getPstm().setInt(4, op.getIdCompte());
			db.getPstm().setInt(5, op.getIdCaissier());
			db.getPstm().setInt(6, op.getIdAgence());
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

	@Override
	public List<Operation> liste(int idop) {
			List<Operation> l_operation=new ArrayList<Operation>();
			String sql="SELECT * FROM operation WHERE idO=?";
			try {
				db.initPrepare(sql);
				db.getPstm().setInt(1, idop);
				rs=db.executeSELECT();
				
				while(rs.next()) {
					Operation op=new Operation();
					op.setIdO(rs.getInt(1));;
					op.setDateop((LocalDate) rs.getObject(2));
					op.setMontant(rs.getInt(3));
					op.setType(rs.getString(4));
					op.setIdCompte(rs.getInt(5));
					op.setIdCaissier(rs.getInt(6));
					op.setIdCaissier(rs.getInt(7));
					l_operation.add(op);	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return l_operation;
			
		}
	
}
