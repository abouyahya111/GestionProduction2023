package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;

public interface ArticlesDAO {
	
	public  void add(Articles e);
	
	public  Articles edit(Articles e);
	
	public  void delete(int id); 
	
	public List<Articles> findAll();
	
	public Articles findById(int id);
	
	public Articles findByCode(String code);
	public Articles findByLibelle(String libelle);
	public List<DetailEstimation> listeMatierePremierByArticleByMP(int id,String codeSubCat, int priotite);
	public List<DetailEstimation> listeMatierePremierByArticleByCategorie(int id,String codeSubCat);
	public List<Articles> listeArticlesNonFabriquer( String requete);
	public List<Articles> listeArticlesByReq( String requete) ;
}
