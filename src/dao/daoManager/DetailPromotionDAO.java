package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailPromotion;

public interface DetailPromotionDAO {
	
	public  void add(DetailPromotion e);
	
	public  DetailPromotion edit(DetailPromotion e);
	
	public  void delete(int id); 
	
	public List<DetailPromotion> findAll();
	
	public DetailPromotion findById(int id);
	public List<DetailPromotion>  findByIdPromo(int id);
	public void ViderSession();
}
