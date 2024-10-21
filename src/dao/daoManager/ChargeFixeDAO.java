package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.DetailEstimation;

public interface ChargeFixeDAO {
	
	public  void add(ChargeFixe e);
	
	public  ChargeFixe edit(ChargeFixe e);
	
	public  void delete(int id); 
	
	public List<ChargeFixe> findAll();
	
	public ChargeFixe findById(int id);
	public ChargeFixe findByCode(String code);

	
}
