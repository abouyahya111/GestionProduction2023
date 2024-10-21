package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MotifPerteMP;

public interface MotifPerteMPDAO {
	
	public  void add(MotifPerteMP e);
	
	public  MotifPerteMP edit(MotifPerteMP e);
	
	public  void delete(int id); 
	
	public List<MotifPerteMP> findAll();
	
	public MotifPerteMP findById(int id);
	
	public MotifPerteMP findByCode(String code);
	

}
