package dao.daoManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.entity.CategorieMp;
import dao.entity.MatierePremier;
import dao.entity.MatierePremierModifier;
import dao.entity.SubCategorieMp;

public interface MatierePremiereModifierDAO {
	


	public  void add(MatierePremierModifier e);
	
	public  MatierePremierModifier edit(MatierePremierModifier e);
	
	public  void delete(int id); 
	
	public List<MatierePremierModifier> findAll();
	
	public MatierePremierModifier findById(int id);
	
	public  List<MatierePremierModifier> findByCode(String code);
	
	
	
	public void ViderSession();

}
