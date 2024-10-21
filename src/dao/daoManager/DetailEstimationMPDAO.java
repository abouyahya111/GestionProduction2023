package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;

public interface DetailEstimationMPDAO {
	
	public  void add(DetailEstimationMP e);
	
	public  DetailEstimationMP edit(DetailEstimationMP e);
	
	public  void delete(int id); 
	
	public List<DetailEstimationMP> findAll();
	
	public DetailEstimationMP findById(int id);
	public List<DetailEstimationMP>  findByIdMP(int id);
	
	public List<DetailEstimationMP> findDetilestimationMPByArticle(int idarticle);
	public List<DetailEstimationMP> findDetilestimationMPActifByArticle(int idarticle);
}
