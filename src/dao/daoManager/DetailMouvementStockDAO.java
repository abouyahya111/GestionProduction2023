package dao.daoManager;

import java.util.List;

import dao.entity.DetailMouvementStock;

public interface DetailMouvementStockDAO {
	
	public DetailMouvementStock findById(int id);
	
	public void add(DetailMouvementStock detailMouvementStock);
	
	public  DetailMouvementStock edit(DetailMouvementStock e);
	
	public  void delete(DetailMouvementStock e); 
	
	public List<DetailMouvementStock> findAll();
            
    public List<DetailMouvementStock> findMouvementStockGlobalByDetailMouvementStock(int mov) ;
}
