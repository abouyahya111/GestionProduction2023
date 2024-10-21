package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.SubCategorieMp;

public interface FournisseurMPDAO {
	
	public  void add(FournisseurMP e);
	
	public  FournisseurMP edit(FournisseurMP e);
	
	public  void delete(int id); 
	
	public List<FournisseurMP> findAll();
	
	public FournisseurMP findById(int id);
	
	public FournisseurMP findByCode(String code);
	
	public List<FournisseurMP> findByCategorie(SubCategorieMp subcategorie);
}
