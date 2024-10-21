package dao.daoManager;

import java.util.List;

import dao.entity.CompteStockMP;

public interface CompteStockMPDAO {
	
	public  void add(CompteStockMP e);
	
	public  CompteStockMP edit(CompteStockMP e);
	
	public  void delete(int id); 
	
	public List<CompteStockMP> findAll();
	
	public CompteStockMP findById(int id);
	
	public CompteStockMP findByCodeMPAnneeMois(String code,int mois,int annee);
	
	public  List<CompteStockMP> findListeByAnneeMois(int moisDebut,int moisFin,int annee);
	public  List<Object>  findSumByAnneeMois(int moisDebut,int moisFin,int annee);

}
