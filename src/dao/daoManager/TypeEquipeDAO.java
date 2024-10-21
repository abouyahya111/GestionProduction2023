package dao.daoManager;

import java.util.List;

import dao.entity.TypeEquipe;

public interface TypeEquipeDAO {
	
	public  void add( TypeEquipe e);
	
	public  TypeEquipe edit(TypeEquipe e);
	
	public  void delete(int id); 
	
	public List<TypeEquipe> findAll();
	
	public TypeEquipe findById(int id);
	
	public TypeEquipe findByCode(String code);

}
