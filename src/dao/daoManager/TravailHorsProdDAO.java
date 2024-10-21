package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.TravailHorsProd;

public interface TravailHorsProdDAO {
	
	public  void add(TravailHorsProd e);
	
	public  TravailHorsProd edit(TravailHorsProd e);
	
	public  void delete(TravailHorsProd e); 
	
	public List<TravailHorsProd> findAll();
	
	public TravailHorsProd findById(int id);
	
	public List<TravailHorsProd> findByDateSitutaionByArticle(Date dateDebut,Date dateFin, boolean heuresEnAttent , Articles articles);
	
	public List<TravailHorsProd> findByEmploye(int id_employe );
	
	public TravailHorsProd  findByEmployeCodeByArticle(int idEmploye,String code, Articles articles);
}
