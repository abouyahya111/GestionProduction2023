package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.CompteurResponsableProd;

public interface CompteurResponsableProdDAO {
	
	public  void add(CompteurResponsableProd e);
	
	public  CompteurResponsableProd edit(CompteurResponsableProd e);
	
	public  void delete(int id); 
	
	public List<CompteurResponsableProd> findAll();
	
	public CompteurResponsableProd findById(int id);
	
	public CompteurResponsableProd findByDateProdPeriode(Date dateProd,String periode,int idEmploye);
	

}
