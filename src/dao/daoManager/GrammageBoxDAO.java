package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;

import dao.entity.GrammageBox;

public interface GrammageBoxDAO {
	
	public  void add(GrammageBox e);
	
	public  GrammageBox edit(GrammageBox e);
	
	public  void delete(int id); 
	
	public List<GrammageBox> findAll();
	
	public GrammageBox findById(int id);
	
	public GrammageBox findByCode(String code);
	
	
	
}
