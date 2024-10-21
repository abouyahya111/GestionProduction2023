package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.TypeSortie;

public interface TypeSortieDAO {
	
	public  void add(TypeSortie e);
	
	public  TypeSortie edit(TypeSortie e);
	
	public  void delete(int id); 
	
	public List<TypeSortie> findAll();
	
	public TypeSortie findById(int id);
	
	public TypeSortie findByLibelle(String libelle);
}
