package dao.daoManager;

import java.util.List;

import dao.entity.Sequenceur;

public interface SequenceurDAO {
	
	public  void add(Sequenceur e);
	
	public  Sequenceur edit(Sequenceur e);
	
	public  void delete(int id); 
	
	public List<Sequenceur> findAll();
	
	public Sequenceur findById(int id);
	

	public Sequenceur findByLibelle(String libelle);

	public Sequenceur findByCodeLibelle(String code, String libelle);
	public Sequenceur findByCode(String code) ;
	

}
