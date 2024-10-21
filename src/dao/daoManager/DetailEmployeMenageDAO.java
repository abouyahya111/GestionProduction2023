package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailEmployeMenage;
import dao.entity.DetailProdGen;
import dao.entity.Employe;

public interface DetailEmployeMenageDAO {
	
	public  void add(DetailEmployeMenage e);
	
	public  DetailEmployeMenage edit(DetailEmployeMenage e);
	
	public  void delete(int id); 
	
	public List<DetailEmployeMenage> findAll();
	
	public DetailEmployeMenage findById(int id);
	public DetailEmployeMenage findByDateByEmploye(Date dateTravail,Employe employe);
	public List<DetailEmployeMenage> ListHeursDetailEmployeMenageParDepot(Date dateDebut,Date dateFin, int depot , String requet);
	public List<DetailEmployeMenage> ListHeursDetailEmployeMenage(Date dateDebut,Date dateFin, String matricule);
	public List<Object[]> MaxDateTravailMenege() ;
}
