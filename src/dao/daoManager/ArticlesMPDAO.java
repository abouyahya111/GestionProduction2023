package dao.daoManager;

import java.util.List;





import dao.entity.ArticlesMP;

import dao.entity.DetailEstimationMP;

public interface ArticlesMPDAO {
	
	public  void add(ArticlesMP e);
	
	public  ArticlesMP edit(ArticlesMP e);
	
	public  void delete(int id); 
	
	public List<ArticlesMP> findAll();
	
	public ArticlesMP findById(int id);
	
	public ArticlesMP findByCode(String code);
	public List<DetailEstimationMP> listeMatierePremierByArticleMPByMP(int id,String codeSubCat) ;
	public List<DetailEstimationMP> listeDetailEstimationMPActifByArticle(int idarticle);
	public ArticlesMP findByNom(String nom);
	

}
