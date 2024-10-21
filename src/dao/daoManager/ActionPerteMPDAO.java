package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.ActionPerteMP;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.PerteMP;

public interface ActionPerteMPDAO {
	
	public  void add(ActionPerteMP e);
	
	public  ActionPerteMP edit(ActionPerteMP e);
	
	public  void delete(int id); 
	
	public List<ActionPerteMP> findAll();
	
	public ActionPerteMP findById(int id);
	
	public ActionPerteMP findByDateByMagasin(Date date, Magasin magasin, String etat);
	
	public void ViderSession();
}
