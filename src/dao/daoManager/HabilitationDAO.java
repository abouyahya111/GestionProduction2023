package dao.daoManager;

import java.util.List;

import dao.entity.Habilitation;

public interface HabilitationDAO {
	
	public  void add(Habilitation e);
	
	public  Habilitation edit(Habilitation e);
	
	public  void delete(int id); 
	
	public List<Habilitation> findAll();
	
	public Habilitation findById(int id);

	public Habilitation findByMenuUtilisateur(int idMenu, int idUser);

	List<Habilitation> findHabilitationByUtilisateur( int idUser);
	
	
}
