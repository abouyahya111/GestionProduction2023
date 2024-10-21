package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;

import dao.entity.GrammageBox;
import dao.entity.forme;

public interface FormeDAO {
	
	public  void add(forme e);
	
	public  forme edit(forme e);
	
	public  void delete(int id); 
	
	public List<forme> findAll();
	
	public forme findById(int id);
	
	public forme findByLibelle(String libelle);
	
	
	
}
