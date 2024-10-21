package dao.daoManager;

import java.util.List;





import dao.entity.Charges;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCoutProduction;


public interface DetailCoutProductionDAO  {
	
	public  void add(DetailCoutProduction e);
	
	public  DetailCoutProduction edit(DetailCoutProduction e);
	
	public  void delete(int id); 
	
	public List<DetailCoutProduction> findAll();
	
	public DetailCoutProduction findById(int id);
	
	public DetailCoutProduction findByCodeCharge(String code,Charges charges);
	


}
