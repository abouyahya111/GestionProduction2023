package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FicheEmployeGlobaleDAO;
import dao.entity.FicheEmploye;
import dao.entity.FicheEmployeGlobale;

public class FicheEmployeGlobaleDAOImpl implements FicheEmployeGlobaleDAO {
	Session session=HibernateUtil.openSession();


	

	


	@Override
	public List<FicheEmployeGlobale> findByDateSitutaionGlobale(Date dateDebut,Date dateFin) {
		
		// TODO Auto-generated method stub
		
				Query query= session.createQuery("select d from FicheEmploye d where dateSituation between :dateDebut and :dateFin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				List<FicheEmployeGlobale> list=query.list();
				
				return  list;
}
	


}
