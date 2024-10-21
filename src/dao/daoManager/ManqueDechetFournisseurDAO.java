package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.ManqueDechetFournisseur;

public interface ManqueDechetFournisseurDAO {
	
	public  void add(ManqueDechetFournisseur e);
	
	public  ManqueDechetFournisseur edit(ManqueDechetFournisseur e);
	
	public  void delete(int id); 
	
	public List<ManqueDechetFournisseur> findAll();
	
	public ManqueDechetFournisseur findById(int id);
	
	public  ManqueDechetFournisseur findByCode(String code,String type);
	public List<ManqueDechetFournisseur> listeManqueDechetFournisseurByEtat(String etat);
	public List<ManqueDechetFournisseur> listeManqueDechetFournisseurByCode(String code);
	public void deleteObject(ManqueDechetFournisseur p);
}
