package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FormeParBox;
import dao.entity.GrammageBox;
import dao.entity.SubCategorieMp;
import dao.entity.forme;

public interface FormeParBoxDAO {
	
	public  void add(FormeParBox e);
	
	public  FormeParBox edit(FormeParBox e);
	
	public  void delete(int id); 
	
	public List<FormeParBox> findAll();
	
	public FormeParBox findById(int id);
	
	public FormeParBox findByFormeBySubCategorie(forme forme,SubCategorieMp subCategorieMp);
	
	public List<FormeParBox> findBySubCategorie(SubCategorieMp subCategorieMp);
	
}
