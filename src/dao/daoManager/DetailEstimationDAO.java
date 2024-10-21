package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;

public interface DetailEstimationDAO {
	
	public  void add(DetailEstimation e);
	
	public  DetailEstimation edit(DetailEstimation e);
	
	public  void delete(int id); 
	
	public List<DetailEstimation> findAll();
	
	public DetailEstimation findById(int id);
	public List<DetailEstimation>  findByIdMP(int id);
	public List<DetailEstimation> findDetilestimationByCategorie(int idarticle) ;
	public List<DetailEstimation> findDetilestimationByArticle(int idarticle);
	public List<DetailEstimation> findDetilestimationActifByArticle(int idarticle);
	public void ViderSession();
}
