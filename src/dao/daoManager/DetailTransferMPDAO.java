package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TypeSortie;

public interface DetailTransferMPDAO {
	
	public  void add(DetailTransferStockMP e);
	
	public  DetailTransferStockMP edit(DetailTransferStockMP e);
	
	public  void delete(int id); 
	
	public List<DetailTransferStockMP> findAll();
	
	public DetailTransferStockMP findById(int id);
	//public List<DetailTransferProduitFini> findStockByMagasinPF(int idArticle,int idMagasin);

	public List<DetailTransferStockMP> findByMP(String codeMP);
	public List<DetailTransferStockMP> findByTransferStockMP(int idtransferStockMP);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMP(Date dateDebut , Date dateFin , MatierePremier mp);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinct(Date dateDebut , Date dateFin ,  MatierePremier mp);
	public List<DetailTransferStockMP> findBytypetransfer(String type,Magasin magasin) ;
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransfer(Magasin magasin);
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByDateTransferByMP(Magasin magasin);
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransferByMP(Magasin magasin , Date dateDebut , Date dateFin , String statut);
	public DetailTransferStockMP findAllTransferStockMPByMPInitialiser(MatierePremier mp,String statut,Depot depot,Magasin magasin);
	public DetailTransferStockMP findAllTransferStockMPByMPByFournisseurInitialiser(MatierePremier mp,String statut,Depot depot,Magasin magasin,FournisseurMP fournisseurMP);
	public DetailTransferStockMP findDetailTransferStockMPByMPByTransferMP(MatierePremier mp,TransferStockMP transferMP);
	public DetailTransferStockMP findDetailTransferStockMPByMPByTransferMPByFournisseur(MatierePremier mp,TransferStockMP transferMP,FournisseurMP fournisseurMP);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) ;
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) ;
	public List<DetailTransferStockMP> findDetailTransferStockMPByMPByTransferMPList(MatierePremier mp,TransferStockMP transferMP);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	public List<DetailTransferStockMP> listDetailTransferStockMPByMPByTransferMPByFournisseur();
	public List<DetailTransferStockMP> listDetailTransferStockMPEnPFByReq(String req);
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByMP(Magasin magasin);
	public List<DetailTransferStockMP> findDetailTransferMPByNumOFStatut(String NUmOF , Magasin magasin , String statut);
	public List<DetailTransferStockMP> findDetailTransferMPchargeouchargeservice(String charge , String  chargeservice);
	public List<Object[]> listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMP(Date dateDebut, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeStockInitialMPByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,  Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeEtatStockMP(Date dateDebut, Magasin magasin );
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listetransfertSortieAncienne(Date dateDebut, Magasin magasin );
	public List<Object[]> listetransfertSortieAncienneByMagasinBySubCategorieByCategorieBayMP(Date dateDebut, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listetransfertSortieActuel(Date dateDebut, Magasin magasin );
	public List<Object[]> listetransfertSortieActuelByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listetransfertEntrerAncienne(Date dateDebut, Magasin magasin );
	public List<Object[]> listetransfertEntrerAncienneByMagasinBySubCategorieByCategorieBayMP(Date dateDebut, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listetransfertEntrerActuel(Date dateDebut, Magasin magasin );
	public List<Object[]> listetransfertEntrerActuelByMagasinBySubCategorieByCategorieBayMP(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<DetailTransferStockMP> ListTransferStockMPDechetmanqueEntreDeuxDatesBYMagasinByMPByFournisseurByType(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin ,FournisseurMP fournisseurMP , TypeSortie typeSortie);
	public List<Object[]> listeInitialEnVrac(int year , String statut , Magasin magasin , int categorie);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesByMagasinByStatutByCodeTransferEntrer(Date dateDebut , Date dateFin , String code,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListHistoriqueReception(String requete);
	public List<DetailTransferStockMP> ListHistoriqueReceptionDechet(String requete) ;
	public List<Object[]> listeSituationEnVrac(Date dateDebut,Date dateFin, SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeSituationTransfert(Magasin magasin, Date dateDebut,Date dateFin , MatierePremier mp);
	public List<Object[]> listeDetailSituationTransfert(Magasin magasin, Date dateDebut,Date dateFin , MatierePremier mp);
	public DetailTransferStockMP findDetailTransferMPByCodeByMP(String codetransfert , MatierePremier mp,String statut);
	public List<Object[]> StockFinaleMPMagasinDechet(Date dateDebut,Date dateFin , Magasin magasin , MatierePremier mp , FournisseurMP fournisseur);
	public List<DetailTransferStockMP> SituationStockFinaleMPMagasinDechet(Date dateDebut,Date dateFin , Magasin magasin , MatierePremier mp , FournisseurMP fournisseur);
	public List<Object[]> SituationStockFinaleMPMagasinDechetSansFournisseur(Date dateDebut,Date dateFin , Magasin magasin , MatierePremier mp , FournisseurMP fournisseur);
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	//public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<DetailTransferStockMP> listeEtatStockMPTransfertEnAttenteThe(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<DetailTransferStockMP> listeEtatStockMPTransfertEnAttenteNonThe(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception (Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp ) ;
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(Date dateDebut,Date dateFin, Magasin magasin , SubCategorieMp subCategorieMp , CategorieMp categorieMp , MatierePremier mp );
	public List<Object[]> MinDate( Magasin magasin );
	public List<DetailTransferStockMP> findDetailTransferMPByCodeTransferByRequete(String requete);
	public  List<DetailTransferStockMP> findDetailTransferMPByCodeByStatut(String codetransfert , String statut);
	public List<DetailTransferStockMP> findDetailTransferMPByRequete(String requete);
	public DetailTransferStockMP DetailTransfertMPByMPBYCodeTransfertBYNumBLReceptionByEtat(MatierePremier mp, String codeTransfert,String NumBlReception,String statut ) ;
	public List<DetailTransferStockMP> ListTransferStockMPReceptionGroupByCodeTransfertByNumBL(Date dateDebut , Date dateFin , String statut);
	public List<DetailTransferStockMP> findByNumBlReceptionEnAttente(String numblReception,String statut );
	public List<Object[]> listeDeclarationReceptionMagasinier(Magasin magasin, Date dateDebut,Date dateFin );
	public List<Object[]> listeDetailDeclarationReceptionMagasinier(Magasin magasin, Date dateDebut,MatierePremier mp);
	public List<Object[]> MontantTotalInitialDesMP(String statut);
	public List<Object[]> MontantTotalInitialDesEnVrac(String statut);
	public List<Object[]> MontantTotalInitialDesEmballages(String statut);
	public void ViderSession();
	
}
