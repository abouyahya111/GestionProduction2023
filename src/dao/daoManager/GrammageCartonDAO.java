package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.GrammageBox;
import dao.entity.GrammageCarton;

public interface GrammageCartonDAO {
	
	public  void add(GrammageCarton e);
	
	public  GrammageCarton edit(GrammageCarton e);
	
	public  void delete(int id); 
	
	public List<GrammageCarton> findAll();
	
	public GrammageCarton findById(int id);
	
	public GrammageCarton findByCode(String code);
	
	
	
}
