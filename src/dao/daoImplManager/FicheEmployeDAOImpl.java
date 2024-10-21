package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FicheEmployeDAO;
import dao.entity.DetailProduction;
import dao.entity.FicheEmploye;

public class FicheEmployeDAOImpl implements FicheEmployeDAO {
	Session session=HibernateUtil.openSession();

	public void add(FicheEmploye e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FicheEmploye edit(FicheEmploye e) {
		
	session.beginTransaction();
	FicheEmploye p= (FicheEmploye)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FicheEmploye p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<FicheEmploye> findAll() {
		return session.createQuery("select c from FicheEmploye c").list();
		}

	public FicheEmploye findById(int id) {
		return (FicheEmploye)session.get(FicheEmploye.class, id);
		}
	


	@Override
	public List<FicheEmploye> findByDateSitutaion(Date dateDebut,Date dateFin, String matricule) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from FicheEmploye d where dateSituation between :dateDebut and :dateFin and employe.matricule=:matricule");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("matricule", matricule);
				
				return  query.list();
}
	
public void deleteObject(FicheEmploye p) {
		
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		
	}

public List<FicheEmploye> findByNumOf(String numOF) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select d from FicheEmploye d where numOF=:numOF");
			query.setParameter("numOF", numOF);
			return  query.list();
}

public FicheEmploye findFicheByEmployeOF(String numOF, int idEmploye) {
	
	// TODO Auto-generated method stub
	Query query= session.createQuery("select  c from FicheEmploye c  where numOF=:numOF and employe.id=:idEmploye");
	query.setParameter("numOF", numOF);
	query.setParameter("idEmploye", idEmploye);
	
	return (FicheEmploye) query.uniqueResult();
}	

public List<Object> findByDateSitutaionAgregation(Date dateDebut,Date dateFin, String matricule) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select id, dateSituation, sum(delaiEmploye) as delaiEmploye, sum(coutTotal) as coutTotal, sum(remise) as remise, sum(avance) as avance from FicheEmploye d where dateSituation between :dateDebut and :dateFin and employe.matricule=:matricule group by dateSituation");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("matricule", matricule);
			
			return query.list();
}	

public FicheEmploye findByPeriodeDateSitutation(Date dateSituation, int idEmploye) {
	
	// TODO Auto-generated method stub
			Query query= session.createQuery("select d from FicheEmploye d where dateSituation=:dateSituation and employe.id=:idEmploye");
		//	query.setParameter("numOF", numOF);
			query.setParameter("dateSituation", dateSituation);
			query.setParameter("idEmploye", idEmploye);
			
			return (FicheEmploye) query.uniqueResult();
}

@Override
public List<FicheEmploye> findByDateSitutaionGlobale(Date dateDebut,Date dateFin,int depot) {
	
	// TODO Auto-generated method stub
	      
			Query query= session.createQuery("select d from FicheEmploye d where dateSituation between :dateDebut and :dateFin and employe.depot.id=:depot");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("depot", depot);
			List<FicheEmploye> list=query.list();
			
			return  list;
}








public void viderSession()
{
	session.clear();
}
}
