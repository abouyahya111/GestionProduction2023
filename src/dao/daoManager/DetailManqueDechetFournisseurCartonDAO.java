package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailManqueDechetFournisseurCarton;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;

public interface DetailManqueDechetFournisseurCartonDAO {
	
	public  void add(DetailManqueDechetFournisseurCarton e);
	
	public  DetailManqueDechetFournisseurCarton edit(DetailManqueDechetFournisseurCarton e);
	
	public  void delete(int id); 
	
	public List<DetailManqueDechetFournisseurCarton> findAll();
	
	public DetailManqueDechetFournisseurCarton findById(int id);
	public List<DetailManqueDechetFournisseurCarton>  findByIdMP(int id);
	public List<DetailManqueDechetFournisseurCarton> findByIdMPByManqueDechetFournisseur(int idmanquedechetfournisseur ,int idmp);
	public List<DetailManqueDechetFournisseurCarton>  findByManqueDechetFournisseur(int idmanquedechetfournisseur);
	public List<Object[]> listeDetailManqueDechetFournisseurGroupByMPByFournisseur(String requete);
	public List<DetailManqueDechetFournisseurCarton>listeDetailManqueDechetFournisseurByMPByFournisseur(MatierePremier mp , FournisseurAdhesive fournisseurAdhesive , String etat);
	public void ViderSession();
}
