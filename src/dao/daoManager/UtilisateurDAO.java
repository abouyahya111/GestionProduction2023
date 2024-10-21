package dao.daoManager;

import java.util.List;

import dao.entity.Utilisateur;

public interface UtilisateurDAO {
	
		public  Utilisateur findUtilisateurByLoginMotPasse(String login,String motPasse);
		
		public Utilisateur findById(int id);
		
		public void add(Utilisateur utilisateur);
		
		
		public  Utilisateur edit(Utilisateur e);
		
		public  void delete(int id); 
		
		public List<Utilisateur> findAll();

		List<Utilisateur> findAllSaufAdmin();
		

}
