package dao.daoManager;

import java.util.List;

import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;

public interface FactureVenteMPDAO {
	
	public  void add(FactureVenteMP e);
	
	public  FactureVenteMP edit(FactureVenteMP e);
	
	public  void delete(int id); 
	
	public List<FactureVenteMP> findAll();
	
	public FactureVenteMP findById(int id);
	
	public FactureVenteMP findByNumFacture(String NumFacture, Magasin magasin) ;
	public List<FactureVenteMP> findByRequete(String requete);
	public void ViderSession();
}
