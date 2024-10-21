package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.DetailCoutHorsProdEnAttent;
import dao.entity.Employe;
import dao.entity.Production;
import dao.entity.TravailHorsProd;

public interface DetailCoutHorsProdEnAttentDAO {
	
	public  void add(DetailCoutHorsProdEnAttent e);
	
	public  DetailCoutHorsProdEnAttent edit(DetailCoutHorsProdEnAttent e);
	
	public  void delete(DetailCoutHorsProdEnAttent e); 
	
	public List<DetailCoutHorsProdEnAttent> findAll();
	
	public DetailCoutHorsProdEnAttent findById(int id);	

	
	public DetailCoutHorsProdEnAttent  findByCodeByEmployeByArticle(String code , Employe employe,Articles articles);
	public List<DetailCoutHorsProdEnAttent>  findByEtat(String etat,Depot depot);
	public List<DetailCoutHorsProdEnAttent>  findBydate(Date datedebut , Date dateFin , Depot depot);
	public List<DetailCoutHorsProdEnAttent>  findByProduction(Production production);
	public List<DetailCoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttent(Date dateDebut,Date dateFin, String matricule  ,String statut);
	public List<DetailCoutHorsProdEnAttent> ListHeursCoutHorsProdEnAttentParDepot(Date dateDebut,Date dateFin, int depot,String statut , String requet);
	public List<DetailCoutHorsProdEnAttent>  findByEtatByDepotByDateByArticle(String etat , Depot depot,Date dateSituation,Articles articles);
	public List<DetailCoutHorsProdEnAttent>  findByCoutHorsProductionEnAttente(CoutHorsProdEnAttent coutHorsProdEnAttent);
}
