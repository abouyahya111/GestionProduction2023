package dao.daoManager;

import java.util.List;

import dao.entity.Employe;
import dao.entity.TypeResEmploye;

public interface EmployeDAO {
	
	public  void add(Employe e);
	
public  Employe edit(Employe e);
	
	public  void delete(int id); 
	
	public List<Employe> findAll();
	
	public List<Employe> findAllEmploye();
	
	public Employe findById(int id);
	
	//public Employe findByCode(String code);

	public Employe findByCode(String code, String numDossier,int depot);

	public List<Employe> findEmployeByType(String Code,String depot);
	
	public List<Employe> findAutreEmployeByType(String codeType);
	
	public List<Employe> findAutreEmploye();
	List<Employe> findByNUmDossier_Matricule_Nom(String numdossier,String matricule,String nom);
	public List<Employe> findByDepot(String depot);
	public List<Employe> findByDepotActifEtSalarie(String depot);
	public List<Employe> findByDepotByNomByResponsabilteByActif(String depot , String nom);
	public Employe findByCINE(String cine,int depot) ;
	public Employe findByCodeEmploye(String code, String numDossier,int depot);
	public int maxIdEmploye();
	public List<Employe> findByDepotByResponsabilite(String depot , TypeResEmploye typeresemploye);
}
