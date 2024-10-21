package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.Employe;
import dao.entity.Production;

public interface DetailProductionDAO {
	
	public  void add(DetailProduction e);
	
	public  DetailProduction edit(DetailProduction e);
	
	public  void delete(int id); 
	
	public List<DetailProduction> findAll();
	
	public DetailProduction findById(int id);
	public List<DetailProduction> ListHeursDetailProduction(Date dateDebut,Date dateFin, String matricule  ,String statut );
	public List<DetailProduction> ListHeursDetailProductionParDepot(Date dateDebut,Date dateFin, int depot,String statut,String requet);
	public List<DetailProduction> ListHeursDetailProductionParDepotparJour(Date dateDebut,Date dateFin, String matricule, int depot,String statut );
	public DetailProduction EmployeDetailProduction(Date dateDebut, String matricule  ,String statut);
	public List<DetailProduction> ListEmployeDetailProductionByProduction(Production production);
	public DetailProduction  DetailProductionByProductionByEmploye(Production production, Employe employe);
	public void  SupprimerDetailProductionByProduction(Production production);
}
