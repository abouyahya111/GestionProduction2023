package dao.daoManager;

import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MotifPerteMP;

public interface ActionMPDAO {
	
	public  void add(ActionMP e);
	
	public  ActionMP edit(ActionMP e);
	
	public  void delete(int id); 
	
	public List<ActionMP> findAll();
	
	public ActionMP findById(int id);
	
	public ActionMP findByCode(String code);
	

}
