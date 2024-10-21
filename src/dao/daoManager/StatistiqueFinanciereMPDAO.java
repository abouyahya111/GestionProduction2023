package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.StatistiqueFinanciaireMP;

public interface StatistiqueFinanciereMPDAO {
	
	public  void add(StatistiqueFinanciaireMP e);
	
	public  StatistiqueFinanciaireMP edit(StatistiqueFinanciaireMP e);
	
	public  void delete(int id); 
	
	public List<StatistiqueFinanciaireMP> findAll();
	
	public StatistiqueFinanciaireMP findById(int id);
	public StatistiqueFinanciaireMP StatistiqueFinanciereMPByEtatInventaire( String etatInventaire);
	public List<StatistiqueFinanciaireMP> listeStatistiqueFinanciereMPByCodeTransfert( String code);
}
