package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.CompteurProduction;

public interface CompteurProductionDAO {
	
	public  void add(CompteurProduction e);
	
	public  CompteurProduction edit(CompteurProduction e);
	
	public  void delete(int id); 
	
	public List<CompteurProduction> findAll();
	
	public CompteurProduction findById(int id);
	
	public CompteurProduction findByDateProdPeriode(Date dateProd,String periode);
	

}
