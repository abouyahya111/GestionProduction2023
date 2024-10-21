package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Parametre;
import dao.entity.ParametreModifier;

public interface ParametreModifierDAO {
	
	public  void add(ParametreModifier e);
	
	public  ParametreModifier edit(ParametreModifier e);
	
	public  void delete(int id); 
	
	public List<ParametreModifier> findAll();
	
	public ParametreModifier findById(int id);
	
	public ParametreModifier findByCode(String code);
	
	public ParametreModifier findByLibelle(String libelle);
	
	public ParametreModifier findByDateByLibelle(Date date, String libelle);
	public List<ParametreModifier> findAllGroupByLibelle();
	public ParametreModifier findUniqueParametreByDateByLibelle(Date date, String libelle);

}
