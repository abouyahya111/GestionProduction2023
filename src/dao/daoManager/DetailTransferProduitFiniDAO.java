package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Magasin;
import dao.entity.TransferStockPF;

public interface DetailTransferProduitFiniDAO {
	

	
	

	
	public  void add(DetailTransferProduitFini e);
	
	public  DetailTransferProduitFini edit(DetailTransferProduitFini e);
	
	public  void delete(int id); 
	
	public List<DetailTransferProduitFini> findAll();
	
	//public List<DetailTransferProduitFini> findStockByMagasinPF(int idArticle,int idMagasin);

	public List<DetailTransferProduitFini> findByArticle(String codeArticle);
	public List<DetailTransferProduitFini> findByTransferStockPF(int idtransferStockPF);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticle(Date dateDebut , Date dateFin , Articles article);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticleDistinct(Date dateDebut , Date dateFin , Articles article);
	public List<DetailTransferProduitFini> findBytypetransfer(String type,Magasin magasin) ;
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransfer(Magasin magasin , Articles articles);
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransferSortie(Magasin magasin, Articles articles);
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByDateTransferByArticle(Magasin magasin, Articles articles);
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByDateTransferByArticleSortie(Magasin magasin, Articles articles);
	public DetailTransferProduitFini findTransferStockPFByArticleBytypetransfer(Articles article ,String type,Magasin magasin);
	
	public List<DetailTransferProduitFini> findByClient(int idclient);
	
	public DetailTransferProduitFini findAllTransferStockPFByPFInitialiser(Articles article,String statut);
	public DetailTransferProduitFini findDetailTransferStockPFByPFByTransferPF(Articles article,TransferStockPF transferPF);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , Client client);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin, Client client );
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByByArticleIdSouFamille(Magasin magasin);
	public void ViderSession();


}
