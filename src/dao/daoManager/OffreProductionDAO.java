package dao.daoManager;

import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MotifPerteMP;
import dao.entity.OffreProduction;
import dao.entity.Production;

public interface OffreProductionDAO {
	
	public  void add(OffreProduction e);
	
	public  OffreProduction edit(OffreProduction e);
	
	public  void delete(int id); 
	
	public List<OffreProduction> findAll();
	public List<OffreProduction> findByProduction(Production production);

	
	public OffreProduction findById(int id);
	
	
	

}
