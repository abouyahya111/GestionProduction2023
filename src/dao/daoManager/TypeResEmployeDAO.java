package dao.daoManager;

import java.util.List;

import dao.entity.TypeResEmploye;

public interface TypeResEmployeDAO {
	
	public  void add( TypeResEmploye e);
	
	public  TypeResEmploye edit(TypeResEmploye e);
	
	public  void delete(int id); 
	
	public List<TypeResEmploye> findAll();
	
	public TypeResEmploye findById(int id);
	
	public TypeResEmploye findByCode(String code);

}
