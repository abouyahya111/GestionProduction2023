package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;

import dao.entity.Depot;

import dao.entity.DetailFactureVenteMP;

import dao.entity.FactureProduction;

import dao.entity.Magasin;


public interface DetailFactureVenteMPDAO {
	
	public  void add(DetailFactureVenteMP e);
	
	public  DetailFactureVenteMP edit(DetailFactureVenteMP e);
	
	public  void delete(int id); 
	
	public List<DetailFactureVenteMP> findAll();
	
	public DetailFactureVenteMP findById(int id);
	
	public List<DetailFactureVenteMP> listeDetailFactureMPByFacture(int idFacture);
	public List<DetailFactureVenteMP> listeDetailFactureMPGroupByMP();
	public void ViderSession();
	
}
