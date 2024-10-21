package dao.daoManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;

import dao.entity.GrammageBox;
import dao.entity.PrimeAnciennte;

public interface PrimeAnciennteDAO {
	
	public  void add(PrimeAnciennte e);
	
	public  PrimeAnciennte edit(PrimeAnciennte e);
	
	public  void delete(int id); 
	
	public List<PrimeAnciennte> findAll();
	
	public PrimeAnciennte findById(int id);
	public long maxId( ) ;
	public 	PrimeAnciennte PrimeByMinByMaxByDatePrim(BigDecimal annee , Date datePrime  );
	
	
	
}
