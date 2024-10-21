package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.CompteurEmployeProd;

public interface CompteurEmployeProdDAO {
	
	public  void add(CompteurEmployeProd e);
	
	public  CompteurEmployeProd edit(CompteurEmployeProd e);
	
	public  void delete(int id); 
	
	public List<CompteurEmployeProd> findAll();
	
	public CompteurEmployeProd findById(int id);
	
	public CompteurEmployeProd findByDateProdPeriode(Date dateProd,String periode,int idEmploye);
	

}
