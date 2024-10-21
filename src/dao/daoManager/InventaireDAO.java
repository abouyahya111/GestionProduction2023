package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.Inventaire;
import dao.entity.Magasin;
import dao.entity.MatierePremier;

public interface InventaireDAO {
	
	public  void add(Inventaire e);
	
	public  Inventaire edit(Inventaire e);
	
	public  void delete(int id); 
	
	public List<Inventaire> findAll();
	
	public List<Inventaire> findByDateByMagasin(Magasin magasin , Date DateOperation,String code,String etat);
	
	public Inventaire findByDateByMagasinByMP(Magasin magasin , Date DateOperation , MatierePremier mp,String code,String etat);
	public Inventaire findByDateByMagasinByMPByFournisseur(Magasin magasin , Date DateOperation , MatierePremier mp,FournisseurMP fournisseurMP,String code,String etat) ;

}
