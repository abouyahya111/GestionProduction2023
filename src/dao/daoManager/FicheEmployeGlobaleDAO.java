package dao.daoManager;

import java.util.Date;
import java.util.List;


import dao.entity.FicheEmployeGlobale;


public interface FicheEmployeGlobaleDAO {
	
	public List<FicheEmployeGlobale> findByDateSitutaionGlobale(Date dateDebut,Date dateFin);
	
}
