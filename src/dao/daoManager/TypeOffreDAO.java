package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.TypeOffre;
import dao.entity.TypeSortie;

public interface TypeOffreDAO {
	
	public  void add(TypeOffre e);
	
	public  TypeOffre edit(TypeOffre e);
	
	public  void delete(int id); 
	
	public List<TypeOffre> findAll();
	
	public TypeOffre findById(int id);
	
	public TypeOffre findBytypeOffre(String typeOffre);
}
