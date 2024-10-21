package dao.daoManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Machine;
import dao.entity.Production;
import util.Constantes;

public interface ProductionDAO {
	
	public  void add(Production e);
	
public  Production edit(Production e);
	
	public  void delete(int id); 
	
	public List<Production> findAll();
	
	public Production findById(int id);
	
	public int maxIdProduction();
	
	public Production findByNumOF(String numOF,String codeDepot);
	
	public List<CoutMP>  listeCoutMP(int idPord);

	public List<Production> listeProductionByDateByPeriode(Date dateProd,String periode);

	public List<DetailProduction> listeDetailProduction(int idPord);

	public List<DetailProdGen> listeDetailProdGen(int idPord);
	
	public List<DetailResponsableProd> listeDetailResponsableProd(int idPord);

	public List<Production> listeProductionEntreDeuxDate(Date dateDebut, Date dateFin);
	public List<Production>  listeProductionTerminerbyDepotEntreDeuxDate(Date dateDebut,Date dateFin,String statut,String depot);
	public Production findByNumOFStatut(String numOF,String statut);
	public void ViderSession();
	public List<Production> listeProductionGroupByArticle() ;
	public List<Object[]> listeProductionParArticleParMois(int mois);
	
	List<CoutMP> listeCoutMPGroupByEnVrac();
	public List<Object[]> listeProductionParEnvracParMois(int mois ,String etat);
	public List<Production> listeProductionEtatCreer(String creer,String lancer,String depot);
	public List<Production> listeProductionEtatTerminer(String terminer,String depot);
	public int NombreProductionTerminerParDate(Date date , String etat);
	public List<CoutMP> listeCoutMPDechetManqueByProduction(int idproduction);
	public List<Object[]> listeSituationDechetManque(String requete , String etat);
	public List<CoutMP> listeSituationManqueEtPLus(String requete);
	public List<Object[]> listeSituationProduction(Date dateDebut,Date dateFin,String statut,String depot);
	public List<Object[]> listeSituationProductionGroupByDate(Date dateDebut,Date dateFin,String statut,String depot);	
	public List<Object[]> NombreEmployeProduction(Date dateDebut,String statut,Machine machine , String depot, Boolean absent, BigDecimal grammage);
	public List<Object[]> NombreEmployeGenerique(Date dateDebut,String statut,Machine machine , String depot, Boolean absent, BigDecimal grammage);
	public List<Object[]> NombreEmployeAdhesif(Date dateDebut,String statut , String depot,Boolean absent) ;
		
	public List<Object[]> NombreEmployeResponsable(Date dateDebut,Boolean absent) ;
	public List<Object[]> NombreEmployeRepos(Date dateDebut, String depot );
	public List<Object[]> listeCoutMPProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeCoutProductionParArticle(Date dateDebut,Date dateFin,String statut,String depot , Articles article);
	public List<Object[]> listeSituationGlobaleCoutProduction(Date dateDebut,Date dateFin,String statut,String depot );
	public List<Production> listeProductionByID();
	public List<Object[]> listeStatistiqueEnVracConsomme(String requete);
	public List<Object[]> listeSituationProductionTotalparArticle(String requete);
	public List<Object[]> listeTonnageProductionparJour(String requete);
	public CoutMP CoutMPParIdProductionGroupByIDCategorie (Integer idProduction);
	public List<Object[]> listeStatistiqueEnVracConsommeLorsDeLaproductionPF(String requete) ;
	public List<Object[]> listeStatistiqueEnVracConsommeLorsDeLaproductionPFGroupByArticle(String requete);
	public List<Object[]> listeArticleFabriqueEtSonType( ) ;
	public List<Object[]> listeSituationProductionParAnnee(Integer year,String statut,String depot);
	public List<Object[]> NombreEmployeProductionParDate(Date dateDebut,String statut,String depot, Boolean absent);
	public List<Object[]> NombreEmployeGeneriqueParDate(Date dateDebut,String statut,String depot, Boolean absent);
	public List<Object[]> ListeNombreEmployeProductionParDate(Date dateDebut,String statut,String depot, Boolean absent);
	public List<Object[]> ListeNombreEmployeGeneriqueParDate(Date dateDebut,String statut,String depot, Boolean absent);
	public List<Object[]> ListeNombreEmployeAdhesif(Date dateDebut,String statut , String depot,Boolean absent) ;
	public List<Object[]> ListeNombreEmployeResponsable(Date dateDebut,Boolean absent);
	public List<Object[]> listeStatistiqueMPConsomme(String requete);
	public int NombreProductionTerminerParDateParDepot(Date date , String etat,String codedepot);
	public List<Production> LesProductionTerminerParDateParDepotGroupByDate(Date date , String etat,String codedepot);
	public List<Production> LesProductionLancerSansTerminerParDateParDepot(Date date , String etat,String codedepot);
	public List<Production> LesProductionTerminerParAnnee(int annee , String etat);
	public List<Production> listeProductionTerminerbyDepotEntreDeuxDateByArticle(Date dateDebut,Date dateFin,String statut,String depot,Articles articles);
	public List<Production> listeProductionEtatTerminerEmballage(String terminer,String depot);
	public List<Production> listeProductionEtatTerminerEnVrac(String terminer,String depot);
}
