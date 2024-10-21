package dao.daoManager;

import java.util.List;

import dao.entity.FactureProduction;

public interface FactureProductionDAO {
	
	public  void add(FactureProduction e);
	
	public  FactureProduction edit(FactureProduction e);
	
	public  void delete(int id); 
	
	public List<FactureProduction> findAll();
	
	public FactureProduction findById(int id);
	
	public FactureProduction findFactureProductionByNumOF(String numOF);

}
