package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CompteMagasinier;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailEstimation;
import dao.entity.DetailFraisDepot;
import dao.entity.FraisDepot;

public interface FraisDepotDAO  {
	
	public  void add(FraisDepot e);
	
	public  FraisDepot edit(FraisDepot e);
	
	public  void delete(int id); 
	
	public List<FraisDepot> findAll();
	
	public FraisDepot findById(int id);
	
	public FraisDepot findByCode(String code);
	public List<DetailFraisDepot> findDetailFraisDepotByFraisDepotAndDate(int code,Date date);
	

}
