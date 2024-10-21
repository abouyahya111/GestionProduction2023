package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailPerteMP;
import dao.entity.DetailTypeSortie;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.PerteMP;
import dao.entity.TypeSortie;

public interface DetailPerteMPDAO {
	
	public  void add(DetailPerteMP e);
	
	public  DetailPerteMP edit(DetailPerteMP e);
	
	public  void delete(int id); 
	
	public List<DetailPerteMP> findAll();
	
	public DetailPerteMP findById(int id);
	public List<DetailPerteMP> listeDetailPerteMPByPerteMP(PerteMP perteMP);

	
}
