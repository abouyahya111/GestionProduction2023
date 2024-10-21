package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.ChargeProduction;
import dao.entity.CoutMP;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.Production;

public interface ChargeProductionDAO {
	
	public  void add(ChargeProduction e);
	
    public  ChargeProduction edit(ChargeProduction e);
	
	public  void delete(int id); 
	
	public List<ChargeProduction> findAll();
	
	public ChargeProduction findById(int id);
	
	public List<DetailChargeFixe> listeDetailhargeFixe(int idChargeFixe);

	public List<DetailChargeVariable> listeDetailhargeVariable(int idChargeVariable);
	public ChargeProduction findbycode(String code);
	public List<ChargeProduction> listeChargeProductionbycode(String code);
	public ChargeProduction findbycodeFix(String code,String fix);
	public List<ChargeProduction> listeChargeProductionbycodeAndDepot(String code,String depot);
}
