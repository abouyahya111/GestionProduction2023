package dao.daoManager;

import java.util.List;

import dao.entity.StockPF;

public interface StockPFDAO {
	
	public  void add(StockPF e);
	
	public  StockPF edit(StockPF e);
	
	public  void delete(int id); 
	
	public List<StockPF> findAll();
	
	public StockPF findStockByArticle(int idArticle );
	
	public StockPF findById(int id);

	List<StockPF> findStockProduitFiniByMagasin(int idMagasin, String requete);

	List<StockPF> findSockNonVideByMagasin(int idMagasin);

	StockPF findStockByMagasinPF(int idArticle, int idMagasin);

}
