package dao.daoManager;

import java.util.List;

import dao.entity.Client;

public interface ClientDAO {
	


	public  void add(Client e);
	
	public  Client edit(Client e);
	
	public  void delete(int id); 
	
	public List<Client> findAll();
	
	public Client findById(int id);

	public List<Client> findListClientByCodeDepot(String codeDepot);

	public	Client findClientByCodeClient(String codeClient);
	




}
