package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.entity.DetailProdGen;
import dao.entity.DetailResponsableProd;

public class DetailResponsableProdDAOImpl implements DetailResponsableProdDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailResponsableProd e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailResponsableProd edit(DetailResponsableProd e) {
		
	session.beginTransaction();
	DetailResponsableProd p= (DetailResponsableProd)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailResponsableProd p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailResponsableProd> findAll() {
		return session.createQuery("select c from DetailResponsableProd c").list();
		}

	public DetailResponsableProd findById(int id) {
		return (DetailResponsableProd)session.get(DetailResponsableProd.class, id);
		}
	

	public List<DetailResponsableProd> ListHeursDetailResponsableProd(Date dateDebut,Date dateFin, String matricule) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailResponsableProd c where c.productionDRP.date between :dateDebut and :dateFin and c.employe.matricule=:matricule");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("matricule", matricule);
				
				return query.list();
	}

	
	public List<DetailResponsableProd> ListHeursDetailResponsableProdParDepot(Date dateDebut,Date dateFin,  int depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailResponsableProd c where c.productionDRP.date between :dateDebut and :dateFin and c.employe.depot.id=:depot and c.employe.id in (67,119,120,121) group by c.productionDRP.date ,c.employe ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				
				return query.list();
	}

	

}
