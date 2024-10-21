package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.Magasin;
import dao.entity.MouvementStockGlobal;

public interface MouvementStockGlobalDAO {
	
	 public MouvementStockGlobal findById(int id);
		
		public void add(MouvementStockGlobal mouvementStockGlobal);
		
		public  MouvementStockGlobal edit(MouvementStockGlobal e);
		
		public  void delete(MouvementStockGlobal e); 
		
		public List<MouvementStockGlobal> findAll();
             
             public MouvementStockGlobal findMouvementStockGlobalByDetailMouvementStock(Date date,int depot,int magasin);
             
             public List<MouvementStockGlobal> findMouvementStockGlobalByDetailMouvementStock(Date date);
             public List<MouvementStockGlobal> ConsulterMouvementStockGlobalByDetailMouvementStock(Date date,Depot depot,Magasin magasin);
}
