package dao.daoManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;

public interface MatierePremiereDAO {
	


	public  void add(MatierePremier e);
	
	public  MatierePremier edit(MatierePremier e);
	
	public  void delete(int id); 
	
	public List<MatierePremier> findAll();
	
	public MatierePremier findById(int id);
	
	public MatierePremier findByCode(String code);
	
	public List<MatierePremier> findMatierePremierSaufCatTHE();
	
	public  List<CategorieMp>  listeCategorieTHE();
	
	public List<MatierePremier> listeMatierePremierByCategorie(String idCat);
	public List<MatierePremier> listeMatierePremierByidcategorie(int idCat) ;
	public List<MatierePremier> listeMatierePremierByidSubcategorie(int idsubCat);
	public List<MatierePremier> listeMatierePremierBycategorieBySubCategorieByMP(SubCategorieMp subcategorie , CategorieMp categorie , MatierePremier mp);
	public List<MatierePremier> findAllDechetMP();
	public List<MatierePremier> listeMatierePremierNonUtiliser(String req,Date dateDu,Date dateAu,String depot,String statut);
	public List<MatierePremier> listeMatiereCadeauAutres(SubCategorieMp subcategorie);
	public List<MatierePremier> listeMatiereCadeauPF(SubCategorieMp subcategorie,BigDecimal unite);
	public List<MatierePremier> listeMatierePremierByReq(String req);
	public List<MatierePremier> listeMatierePremierByClient(Client client) ;
	public void ViderSession();

}
