package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CompteMagasinier;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailEstimation;

public interface DetailCompteMagasinierDAO  {
	
	public  void add(DetailCompteMagasinier e);
	
	public  DetailCompteMagasinier edit(DetailCompteMagasinier e);
	
	public  void delete(int id); 
	
	public List<DetailCompteMagasinier> findAll();
	
	public DetailCompteMagasinier findById(int id);
	
	public List<DetailCompteMagasinier> listeDetailCompteMagasinierByCompteMagasinier (CompteMagasinier compteMagasinier) ;
	

}
