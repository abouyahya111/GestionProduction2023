package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProdGen;
import dao.entity.DetailResponsableProd;

public interface DetailResponsableProdDAO {
	
	public  void add(DetailResponsableProd e);
	
	public  DetailResponsableProd edit(DetailResponsableProd e);
	
	public  void delete(int id); 
	
	public List<DetailResponsableProd> findAll();
	
	public DetailResponsableProd findById(int id);
	public List<DetailResponsableProd> ListHeursDetailResponsableProd(Date dateDebut,Date dateFin, String matricule);
	public List<DetailResponsableProd> ListHeursDetailResponsableProdParDepot(Date dateDebut,Date dateFin,  int depot) ;
	
	
}
