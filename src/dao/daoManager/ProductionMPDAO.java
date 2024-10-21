package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.Production;
import dao.entity.ProductionMP;

public interface ProductionMPDAO {
	
	public  void add(ProductionMP e);
	
public  ProductionMP edit(ProductionMP e);
	
	public  void delete(int id); 
	
	public List<ProductionMP> findAll();
	
	public ProductionMP findById(int id);
	
	public int maxIdProductionMP();
	
	public ProductionMP findByNumOFMP(String numOFMP,String codeDepot);
	
	public List<CoutProdMP> listeCoutProdMP(int idPord);

	public List<ProductionMP> listeProductionMPByDateByPeriode(Date dateProd,String periode);
	public List<ProductionMP> listeProductionMPEtatTerminer(String etat,String codeDepot);
	public List<DetailProductionMP> listeDetailProduction(int idPord);
	
	public List<ProductionMP> listeProductionMPEntreDeuxDate(Date dateDebut, Date dateFin);
	public ProductionMP findByNumOFStatut(String numOF,String statut);
	public List<ProductionMP> listeProductionMPEtatCreer(String creer,String lancer,String codeDepot);
	public List<CoutProdMP> listeCoutProdMPDechetManqueByProduction(int idproduction);
	public List<ProductionMP> LesProductionTerminerParAnnee(int annee , String etat);
}
