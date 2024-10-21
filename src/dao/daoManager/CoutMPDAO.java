package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;

public interface CoutMPDAO {
	
	public  void add(CoutMP e);
	
	public  CoutMP edit(CoutMP e);
	
	public  void delete(int id); 
	
	public List<CoutMP> findAll();
	
	public CoutMP findById(int id);
	public List<Object[]> listeCoutProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutMPParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutDetailProdResParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutDetailProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutDetailProdGenParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeSumQuantiteReelParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	
	
	public List<Object[]> listeCoutMPParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutDetailProdResParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutDetailProductionParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutDetailProdGenParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Production> listeSumQuantiteReelParProduction(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]>  QuantiteConsommeOffre(MatierePremier mp , String offre, FournisseurMP fournisseur,String depot);
	
	public void ViderSession();
	
	

}
