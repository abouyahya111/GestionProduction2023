package dao.daoManager;

import java.util.List;

import dao.entity.ActionMP;
import dao.entity.ActionPerteMP;
import dao.entity.Articles;
import dao.entity.DetailActionPerteMP;
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

public interface DetailActionPerteMPDAO {
	
	public  void add(DetailActionPerteMP e);
	
	public  DetailActionPerteMP edit(DetailActionPerteMP e);
	
	public  void delete(int id); 
	
	public List<DetailActionPerteMP> findAll();
	
	public DetailActionPerteMP findById(int id);
	public List<DetailActionPerteMP> listeDetailActionPerteMPByActionPerteMP(ActionPerteMP ActionperteMP);
	public List<DetailActionPerteMP> listeDetailActionPerteMPByDetailPerteMP(DetailPerteMP DetailnperteMP);
	public void ViderSession();
	
}
