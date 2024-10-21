package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.ProductionMP;

public interface DetailProductionMPDAO {
	
	public  void add(DetailProductionMP e);
	
	public  DetailProductionMP edit(DetailProductionMP e);
	
	public  void delete(int id); 
	
	
	
	public DetailProductionMP findById(int id);
	
	public List<DetailProductionMP> ListHeursDetailProductionMP(Date dateDebut,Date dateFin, String matricule,String statut);
	public List<DetailProductionMP> ListHeursDetailProductionMPParDepot(Date dateDebut,Date dateFin, int depot  ,String statut,String requet);
	public List<DetailProductionMP> ListHeursDetailProductionMPParDepotParJour(Date dateDebut,Date dateFin,String matricule, int depot  ,String statut);
	public DetailProductionMP EmployeDetailProductionMP(Date dateDebut, String matricule,String statut);
	public List<DetailProductionMP> ListHeursDetailProductionMPParProductionMP(ProductionMP productionMP);

}
