package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Depot;
import dao.entity.DetailTypeSortie;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.TransferStockMP;
import dao.entity.TypeSortie;

public interface TransferStockMPDAO {
	
	public  void add(TransferStockMP e);
	
public  TransferStockMP edit(TransferStockMP e);
	
	public  void delete(int id); 
	
	public List<TransferStockMP> findAll();
	
	public TransferStockMP findById(int id);

	public int maxIdStock();
	
	public TransferStockMP findTransferByCode(String codeTransfer);
	public TransferStockMP findTransferByCodeStatut(String codeTransfer , String statut);
	public List<TransferStockMP> findTransferByStatut(String statut,Date dateDebut , Date dateFin , Depot depot) ;
	public List<TransferStockMP> findTransferByStatutChargeouService(String charge,String service);
	public List<TransferStockMP> findTransferByStatutSaufTransfer(String statut,Date dateDebut , Date dateFin , Depot depot);
	public List<TransferStockMP> findTransferConsultationBonSortieMP( Depot depot ,  String requet);
	public List<TransferStockMP> findTransferByStatutSaufTransferAllRegion(String statut,Date dateDebut , Date dateFin );
	public List<TransferStockMP> listTransferByCode(String codeTransfer,Date dateDebut , Date dateFin );
	public void ViderSession();
	public List<TransferStockMP> ListeCodeTransfertEnAttente(String statut, Depot depot);
	public List<TransferStockMP> listTransferMPProduction(String req);
	public List<TransferStockMP> listTransferMarchandiseDeplacer(String req);
	public void deleteObject(TransferStockMP p);
	public List<TransferStockMP> findListeTransferByCodeStatut(String codeTransfer , String statut);
	public List<TransferStockMP> findListeTransferByCodeStatutByNumBLReception(String codeTransfer , String statut,String NumBL);
	public  TransferStockMP findListeTransferByCodeStatutByNumBLReceptionByMP(String codeTransfer , String statut,String NumBL, MatierePremier mp);
	public  TransferStockMP findListeTransferByCodeStatutByNumBLReceptionUnique(String codeTransfer , String statut,String NumBL);
	public List<TransferStockMP> findReceptionEnAttenteNonImporter(String statut,Date dateDebut , Date dateFin , Depot depot) ;
	public List<TransferStockMP> findListeTransferByDateByStatut(Date date , String statut);
	public List<TransferStockMP> listTransferByStatut(String statut);
	public List<TransferStockMP> listTransferByStatutByLikeCodeTransfert(String statut, String likeCodeTransfert);
}
