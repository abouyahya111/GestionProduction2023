package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.ConditionOffre;
import dao.entity.DetailEstimation;
import dao.entity.TypeOffre;
import dao.entity.TypeSortie;

public interface ConditionOffreDAO {
	
	public  void add(ConditionOffre e);
	
	public  ConditionOffre edit(ConditionOffre e);
	
	public  void delete(int id); 
	
	public List<ConditionOffre> findAll();
	
	public ConditionOffre findById(int id);
	
	public ConditionOffre findByconditionOffre(String condition);
	
}
