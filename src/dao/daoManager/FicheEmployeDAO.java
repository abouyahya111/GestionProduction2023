package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProduction;
import dao.entity.FicheEmploye;
import dao.entity.RecapFicheEmploye;

public interface FicheEmployeDAO {
	
	public  void add(FicheEmploye e);
	
	public  FicheEmploye edit(FicheEmploye e);
	
	public  void delete(int id); 
	
	public List<FicheEmploye> findAll();
	
	public FicheEmploye findById(int id);
	
	public List<FicheEmploye> findByDateSitutaion(Date dateDebut,Date dateFin,String matricule);
	
	public List<FicheEmploye> findByNumOf(String numOF);
	
	public void deleteObject(FicheEmploye p);
	
	public FicheEmploye findFicheByEmployeOF(String numOF, int idEmploye);

	public List<Object> findByDateSitutaionAgregation(Date date,Date date2, String text);
	
	public FicheEmploye findByPeriodeDateSitutation(Date date, int idEmploye);
	
	public List<FicheEmploye> findByDateSitutaionGlobale(Date dateDebut,Date dateFin,int depot);
	
	public void viderSession();
	
}
