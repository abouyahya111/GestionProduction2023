package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;

import dao.entity.GrammageBox;
import dao.entity.forme;
import dao.entity.service;

public interface ServiceDAO {
	
	public  void add(service e);
	
	public  service edit(service e);
	
	public  void delete(int id); 
	
	public List<service> findAll();
	
	public service findById(int id);
	
	public service findByLibelle(String libelle);
	
	
	
}
