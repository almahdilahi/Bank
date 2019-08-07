package method;

import java.sql.ResultSet;
import java.util.List;

import entities.Employe;
import entities.Employeur;

public interface IEmploye {
	public Employe getLoginUser(String login,String password);
	public Employe getLoginAdmin(String login,String password);
	public List<Employe> liste();
	public int add(Employe u);
	public int addEmployeur(entities.Employeur u);
	public boolean update(Employe u);
	public boolean delete(int id);
	public Employe getEmployeById(int id);
	public ResultSet cbgetidiemp(String nomint, String table, String id, String nom);
	
}
