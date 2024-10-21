package dao.daoManager;

import java.util.List;

import dao.entity.CompteurAbsenceEmploye;

public interface CompteurAbsenceEmployeDAO {
	
	public  void add(CompteurAbsenceEmploye e);
	
	public  CompteurAbsenceEmploye edit(CompteurAbsenceEmploye e);
	
	public  void delete(int id); 
	
	public List<CompteurAbsenceEmploye> findAll();
	
	public CompteurAbsenceEmploye findById(int id);
	
	public CompteurAbsenceEmploye findByDateAbsencePeriode(String code,int idEmploye);
	

}
