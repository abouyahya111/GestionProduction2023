package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailTypeSortie;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.TypeSortie;

public interface DetailTypeSortieDAO {
	
	public  void add(DetailTypeSortie e);
	
	public  DetailTypeSortie edit(DetailTypeSortie e);
	
	public  void delete(int id); 
	
	public List<DetailTypeSortie> findAll();
	
	public DetailTypeSortie findById(int id);
	public List<DetailTypeSortie> listeDetailTypeSortieByTypeSortie(TypeSortie type);
	public DetailTypeSortie DetailTypeSortieByTypeSortie(String type);
	
}
