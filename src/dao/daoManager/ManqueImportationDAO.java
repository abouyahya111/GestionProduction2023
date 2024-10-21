package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.ManqueImportation;

public interface ManqueImportationDAO {
	
	public  void add(ManqueImportation e);
	
	public  ManqueImportation edit(ManqueImportation e);
	
	public  void delete(int id); 
	
	public List<ManqueImportation> findAll();
	
	public ManqueImportation findById(int id);
	
	public List<ManqueImportation> findByRequete(String req);
	public List<ManqueImportation> findByEtatGroupByNumReception(String etat);
	public List<ManqueImportation> findByEtatByNumReception(String etat,String numReception);
}
