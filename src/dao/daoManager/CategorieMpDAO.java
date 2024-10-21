package dao.daoManager;

import java.util.List;

import dao.entity.CategorieMp;
import dao.entity.SubCategorieMp;

public interface CategorieMpDAO {
	
public  void add(CategorieMp e);
	
	public  CategorieMp edit(CategorieMp e);
	
	public  void delete(int i); 
	
	
	public  CategorieMp findById(int id);
	
	public   List<CategorieMp> findAll();
	public List<CategorieMp> findBySubcategorie(int id) ;
	public CategorieMp findByCode(String code);
}
