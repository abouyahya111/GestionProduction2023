package dao.daoManager;

import java.util.List;

import dao.entity.SubCategorieMp;

public interface SubCategorieMPDAO {
	


	public  void add(SubCategorieMp e);
	
	public  SubCategorieMp edit(SubCategorieMp e);
	
	public  void delete(int id); 
	
	public List<SubCategorieMp> findAll();
	
	public SubCategorieMp findById(int id);
	public List<SubCategorieMp> findAllSauf(String Cat);
	public SubCategorieMp findByCode(String Cat);


}
