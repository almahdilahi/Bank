package method;

import java.sql.ResultSet;
import java.util.List;

import entities.Compte;

public interface ICompte {

	public List<Compte> liste();
	public int add(Compte c,String type);
	public boolean updatesoldeadd(int montant,int id);
	public boolean updatesoldedel(int montant,int id);
	public boolean delete(int id);
	public Compte getCompteById(int id);
	public ResultSet cbgetid(String nomint, String table, String id, String nom);
}
