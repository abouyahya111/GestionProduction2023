package dao.daoManager;

import java.util.List;

import dao.entity.CompteurTransferMP;

public interface CompteurTransferMPDAO {
	
	public  void add( CompteurTransferMP e);
	
	public   CompteurTransferMP edit( CompteurTransferMP e);
	
	public  void delete(int id); 
	
	public List< CompteurTransferMP> findAll();
	
	public  CompteurTransferMP findById(int id);
	
	public  CompteurTransferMP findByDepot(String codeDepot);
	

}
