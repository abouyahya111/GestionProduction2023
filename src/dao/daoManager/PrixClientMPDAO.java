package dao.daoManager;

import java.util.List;

import dao.entity.PrixClientMP;

public interface PrixClientMPDAO {
	
	public  void add(PrixClientMP e);
	
	public  PrixClientMP edit(PrixClientMP e);
	
	public  void delete(int id); 
	
	public List<PrixClientMP> findAll();
	
	public List<PrixClientMP> findAllByClient(String codeClient);
	
	public PrixClientMP findById(int id);
	
	public PrixClientMP findPrixClientByMatierePremier(String codeMP);
	
	public PrixClientMP findPrixByClientMP(String codeMP,String codeClient,String codefournisseur);
	
	public List<PrixClientMP> findAllByFournisseurClient(String codefournisseur,String codeClient);


	
	

}
