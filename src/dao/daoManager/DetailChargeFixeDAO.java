package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailEstimation;

public interface DetailChargeFixeDAO  {
	
	public  void add(DetailChargeFixe e);
	
	public  DetailChargeFixe edit(DetailChargeFixe e);
	
	public  void delete(int id); 
	
	public List<DetailChargeFixe> findAll();
	
	public DetailChargeFixe findById(int id);
	


}
