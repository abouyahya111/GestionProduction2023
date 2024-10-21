package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProdGen;
import dao.entity.Employe;
import dao.entity.Production;

public interface DetailProdGenDAO {
	
	public  void add(DetailProdGen e);
	
	public  DetailProdGen edit(DetailProdGen e);
	
	public  void delete(int id); 
	
	public List<DetailProdGen> findAll();
	
	public DetailProdGen findById(int id);
	
	public List<DetailProdGen> findByDateProdPeriode(Date dateProd,String periode);
	
	public DetailProdGen findByDateProdPeriodeEmploye(Date dateProd,String periode,int idEmploye);
	public List<DetailProdGen> ListHeursDetailProdGen(Date dateDebut,Date dateFin, String matricule,String statut);
	public List<DetailProdGen> ListHeursDetailProdGenParDepot(Date dateDebut,Date dateFin,  int depot ,String statut,String requet);
	public List<DetailProdGen> ListHeursDetailProdGenParDepotParJour(Date dateDebut,Date dateFin, String matricule,  int depot ,String statut);
	public DetailProdGen EmployeDetailProdGen(Date dateDebut, String matricule,String statut);
	public DetailProdGen  DetailProdGenByProductionByEmploye(Production production, Employe employe);
	public void  SupprimerDetailProductionEmballageByProduction(Production production);

}
