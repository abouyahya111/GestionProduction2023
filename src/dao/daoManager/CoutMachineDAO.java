package dao.daoManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CoutMachine;
import dao.entity.DetailEstimation;

import dao.entity.GrammageBox;
import dao.entity.forme;

public interface CoutMachineDAO {
	
	public  void add(CoutMachine e);
	
	public  CoutMachine edit(CoutMachine e);
	
	public  void delete(int id); 
	
	public List<CoutMachine> findAll();
	
	public CoutMachine findById(int id);
	
	
    public CoutMachine CoutMachineParDateParTonnageParForme(Date date , BigDecimal tonnage,forme forme,CategorieMp categorie);
	
	public CoutMachine CoutMachineParDateParMaxTonnageParForme(Date date , BigDecimal tonnage,forme forme,CategorieMp categorie);
	
}
