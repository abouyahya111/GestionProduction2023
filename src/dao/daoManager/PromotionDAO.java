package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.Promotion;

public interface PromotionDAO {
	
	public  void add(Promotion e);
	
	public  Promotion edit(Promotion e);
	
	public  void delete(int id); 
	
	public List<Promotion> findAll();
	
	public Promotion findById(int id);
	
	public int maxIdPromotion();
	public Promotion findByCode(String code);
	public List<Promotion> findByArticle(int code);
	public List<Promotion> findByArticleActif(int code);
	public List<Promotion> findByReq(String req);
	

}
