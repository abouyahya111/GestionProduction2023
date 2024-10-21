package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Depot;
import dao.entity.Employe;
import dao.entity.EmployeRepos;
import dao.entity.TypeResEmploye;

public interface EmployeReposDAO {
	
	public  void add(EmployeRepos e);
	
public  EmployeRepos edit(EmployeRepos e);
	
	public  void delete(int id); 
	
	public List<EmployeRepos> findAll();
	
	
	
	public EmployeRepos findById(int id);
	public List<EmployeRepos> findByDepotByDate(Date datesituation, String depot);
	//public Employe findByCode(String code);
	public List<EmployeRepos> findByRequete(Date datedebut,Date datefin, Depot depot,String  requete);


}
