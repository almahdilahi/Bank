package method;

import java.util.List;

import entities.Client;

public interface IClient {
	public List<Client> liste();
	public int add(Client cl);
	public boolean update(Client c);
	public boolean delete(int id);
	public Client getClientById(int id);
	public Client getLoginClient(String login, String password);

}
