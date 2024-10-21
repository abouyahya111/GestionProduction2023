package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Depot;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.PerteMP;

public interface PerteMPDAO {
	
	public  void add(PerteMP e);
	
	public  PerteMP edit(PerteMP e);
	
	public  void delete(int id); 
	
	public List<PerteMP> findAll();
	
	public PerteMP findById(int id);
	
	public PerteMP findByDateByMagasin(Date date, Magasin magasin, String etat);
	public List<PerteMP> listDesPertesValiderParDepot(String depot , String etat);
	public PerteMP findByDateByMagasinByNumPerte(String numPerte, Date date, Magasin magasin );
	public PerteMP findByDateByMagasinByNumPerteParEtat(String numPerte, Date date, Magasin magasin , String etat);
	public List<PerteMP> listDesPertesParDepot(String Codedepot );
	public void ViderSession();
}
