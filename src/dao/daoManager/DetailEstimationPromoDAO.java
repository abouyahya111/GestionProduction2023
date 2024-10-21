package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;

public interface DetailEstimationPromoDAO {
	
	public  void add(DetailEstimationPromo e);
	
	public  DetailEstimationPromo edit(DetailEstimationPromo e);
	
	public  void delete(int id); 
	
	public List<DetailEstimationPromo> findAll();
	
	public DetailEstimationPromo findById(int id);
	public List<DetailEstimationPromo>  findByIdPromo(int id);
	public List<DetailEstimationPromo>  findByReq(String req) ;
	public void ViderSession();
}
