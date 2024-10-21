package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailResponsableProd;

public interface DetailProdResDAO {
	
	public  void add(DetailProdRes e);
	
	public  DetailProdRes edit(DetailProdRes e);
	
	public  void delete(int id); 
	
	public List<DetailProdRes> findAll();
	
	public DetailProdRes findById(int id);
	public List<DetailProdRes> ListHeursDetailResponsableProd(Date dateDebut,Date dateFin, String matricule);
	public List<DetailProdRes> ListHeursDetailResponsableProdParDepot(Date dateDebut,Date dateFin,  int depot,String requet) ;
	public List<DetailProdRes> ListHeursDetailResponsableProdParDepotParJour(Date dateDebut,Date dateFin,  int depot, String matricule);
	public DetailProdRes EmployeDetailResponsableProd(Date dateDebut, String matricule);
	
}
