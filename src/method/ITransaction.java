package method;

import java.sql.ResultSet;
import java.util.List;

import entities.Operation;

public interface ITransaction {
	public int adddepot(Operation op);
	public int addretrait(Operation op);
	public int addvirement(Operation op);
	public List<Operation> liste(int idop);
	public ResultSet cbgetid(String nomint, String table, String id, String nom);
}
