package dao.daoManager;

import java.util.List;

import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.Equipe;

public interface EquipeDAO {
	
	public  void add(Equipe e);
	
public  Equipe edit(Equipe e);
	
	public  void delete(int id); 
	
	public List<Equipe> findAll();
	
	public Equipe findById(int id);
	
	public Equipe findByCodeNom(String code, String nom);
	
	public List<Equipe> findListeEquipeByType(String typeEquipe, String codeDepot);

	public List<Employe> findListeEmployeByEquipe(int idEquipe);
	public Equipe findByCodeNomDepot(String code, String nom,int depot);

}
