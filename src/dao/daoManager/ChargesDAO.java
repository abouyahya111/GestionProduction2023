package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.Charges;
import dao.entity.DetailEstimation;

public interface ChargesDAO {
	
	public  void add(Charges e);
	
	public  Charges edit(Charges e);
	
	public  void delete(int id); 
	
	public List<Charges> findAll();
	
	public Charges findById(int id);
	public Charges findByCodeVariable(String code);
	public Charges findByCodeFixe(String code);
	public List<Charges> findAllFixe() ;
	public List<Charges> findAllVariable();
	
}
