package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.ManqueDechetFournisseurCarton;

public interface ManqueDechetFournisseurCartonDAO {
	
	public  void add(ManqueDechetFournisseurCarton e);
	
	public  ManqueDechetFournisseurCarton edit(ManqueDechetFournisseurCarton e);
	
	public  void delete(int id); 
	
	public List<ManqueDechetFournisseurCarton> findAll();
	
	public ManqueDechetFournisseurCarton findById(int id);
	
	public ManqueDechetFournisseurCarton findByCode(String code);
	public List<ManqueDechetFournisseurCarton> listeManqueDechetFournisseurByEtat(String etat);

}
