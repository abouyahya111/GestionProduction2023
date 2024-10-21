package dao.daoManager;

import java.util.List;

import dao.entity.EmployeBloque;

public interface EmployeBloqueDAO {
	
	public  void add(EmployeBloque e);
	
	public  EmployeBloque edit(EmployeBloque e);
	
	public  void delete(int id); 
	
	public List<EmployeBloque> findAll();
	
	public EmployeBloque findById(int id);
	
	public EmployeBloque findByCode(String code);

}
