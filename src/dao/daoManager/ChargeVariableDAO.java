package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.ChargeVariable;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.DetailEstimation;

public interface ChargeVariableDAO {
	
	public  void add(ChargeVariable e);
	
	public  ChargeVariable edit(ChargeVariable e);
	
	public  void delete(int id); 
	
	public List<ChargeVariable> findAll();
	
	public ChargeVariable findById(int id);
	public ChargeVariable findByCode(String code);

	
}
