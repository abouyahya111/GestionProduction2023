package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;
import dao.entity.ProductionMP;

public interface CoutProductionMPDAO {
	
	public  void add(CoutProdMP e);
	
	public  CoutProdMP edit(CoutProdMP e);
	
	public  void delete(int id); 
	
	public List<CoutProdMP> findAll();
	
	public CoutProdMP findById(int id);
	public List<CoutProdMP> listeCoutProdMPParProduction(ProductionMP productionMP);
	
	public void ViderSession();
	
	

}
