package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.Production;
import dao.entity.TravailHorsProd;

public interface CoutHorsProdEnAttentDAO {
	
	public  void add(CoutHorsProdEnAttent e);
	
	public  CoutHorsProdEnAttent edit(CoutHorsProdEnAttent e);
	
	public  void delete(CoutHorsProdEnAttent e); 
	
	public List<CoutHorsProdEnAttent> findAll();
	
	public CoutHorsProdEnAttent findById(int id);	

	
	public CoutHorsProdEnAttent  findByCodeByEmployeByArticle(String code , Employe employe,Articles articles);
	public List<CoutHorsProdEnAttent>  findByEtat(String etat,Depot depot);
	public List<CoutHorsProdEnAttent>  findBydate(Date datedebut , Date dateFin , Depot depot);
	public List<CoutHorsProdEnAttent>  findByProduction(Production production);
	public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttent(Date dateDebut,Date dateFin, String matricule  ,String statut);
	public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentParDepot(Date dateDebut,Date dateFin, int depot,String statut , String requet);
	public List<CoutHorsProdEnAttent>  findByEtatByDepotByDateByArticle(String etat , Depot depot,Date dateSituation,Articles articles);
	public List<CoutHorsProdEnAttent>  findBydateByTypeTravail(Date datedebut , Date dateFin , Depot depot,String typeTravail);
	public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentByDate(Date dateDebut,Date dateFin, String matricule);
	public List<CoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentParDepotParDate(Date dateDebut,Date dateFin, int depot, String requet);
}
