package dao.daoManager;

import java.util.List;

import dao.entity.CompteurNumDossier;

public interface CompteurNumDossierDAO {
	
	public  void add(CompteurNumDossier e);
	
	public  CompteurNumDossier edit(CompteurNumDossier e);
	
	public  void delete(int id); 
	
	public List<CompteurNumDossier> findAll();
	
	public CompteurNumDossier findById(int id);
	
	public CompteurNumDossier findNumByAnnee(String annee);
	

}
