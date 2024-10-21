package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Parametre;

public interface ParametreDAO {
	
	public  void add(Parametre e);
	
	public  Parametre edit(Parametre e);
	
	public  void delete(int id); 
	
	public List<Parametre> findAll();
	
	public Parametre findById(int id);
	
	public Parametre findByCode(String code);
	
	public Parametre findByLibelle(String libelle);
	
	public Parametre findByDateByLibelle(Date date, String libelle);
	public List<Parametre> findAllGroupByLibelle();
	public Parametre findUniqueParametreByDateByLibelle(Date date, String libelle);

}
