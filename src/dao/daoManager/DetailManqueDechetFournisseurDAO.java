package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;

public interface DetailManqueDechetFournisseurDAO {
	
	public  void add(DetailManqueDechetFournisseur e);
	
	public  DetailManqueDechetFournisseur edit(DetailManqueDechetFournisseur e);
	
	public  void delete(int id); 
	
	public List<DetailManqueDechetFournisseur> findAll();
	
	public DetailManqueDechetFournisseur findById(int id);
	public List<DetailManqueDechetFournisseur>  findByIdMP(int id);
	public List<DetailManqueDechetFournisseur> findByIdMPByManqueDechetFournisseur(int idmanquedechetfournisseur ,int idmp);
	public List<DetailManqueDechetFournisseur>  findByManqueDechetFournisseur(int idmanquedechetfournisseur);
	public List<DetailManqueDechetFournisseur>  findByManqueDechetFournisseurByCategorieEmballage(int idmanquedechetfournisseur);
	public List<DetailManqueDechetFournisseur>  findByManqueDechetFournisseurByCategorieThe(int idmanquedechetfournisseur);
	public List<Object[]> listeDetailManqueDechetFournisseurGroupByMPByFournisseur(MatierePremier mp , FournisseurMP fournisseur,String etat,String type,Magasin magasin);
	public List<DetailManqueDechetFournisseur>listeDetailManqueDechetFournisseurByMPByFournisseur(MatierePremier mp , FournisseurMP fournisseur , String etat,String type,Magasin magasin);
	public List<DetailManqueDechetFournisseur>  findByIdMPByManqueDechetFournisseurByFournisseur(int idmanquedechetfournisseur ,int idmp, FournisseurMP fournisseurMP);
	public void ViderSession();
}
