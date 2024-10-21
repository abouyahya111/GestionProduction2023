package dao.daoManager;

import java.util.List;



import dao.entity.DetailChargeVariable;


public interface DetailChargeVariableDAO  {
	
	public  void add(DetailChargeVariable e);
	
	public  DetailChargeVariable edit(DetailChargeVariable e);
	
	public  void delete(int id); 
	
	public List<DetailChargeVariable> findAll();
	
	public DetailChargeVariable findById(int id);
	


}
