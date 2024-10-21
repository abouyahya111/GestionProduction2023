package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;

public interface FournisseurAdhesiveDAO {
	
	public  void add(FournisseurAdhesive e);
	
	public  FournisseurAdhesive edit(FournisseurAdhesive e);
	
	public  void delete(int id); 
	
	public List<FournisseurAdhesive> findAll();
	
	public FournisseurAdhesive findById(int id);
	
	public FournisseurAdhesive findByCode(String code);
	

}
