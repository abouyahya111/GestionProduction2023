package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailResponsableProd;

public class DetailProdResDAOImpl implements DetailProdResDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailProdRes e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailProdRes edit(DetailProdRes e) {
		
	session.beginTransaction();
	DetailProdRes p= (DetailProdRes)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailProdRes p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailProdRes> findAll() {
		return session.createQuery("select c from DetailProdRes c").list();
		}

	public DetailProdRes findById(int id) {
		return (DetailProdRes)session.get(DetailProdRes.class, id);
		}
	

	public List<DetailProdRes> ListHeursDetailResponsableProd(Date dateDebut,Date dateFin, String matricule) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProdRes c where c.dateProduction between :dateDebut and :dateFin and c.employe.matricule=:matricule order by c.dateProduction");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("matricule", matricule);
				
				return query.list();
	}

	public DetailProdRes EmployeDetailResponsableProd(Date dateDebut, String matricule) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProdRes c where c.dateProduction =:dateDebut  and c.employe.matricule=:matricule order by c.dateProduction");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("matricule", matricule);
				
				return (DetailProdRes) query.uniqueResult();
	}
	
	public List<DetailProdRes> ListHeursDetailResponsableProdParDepot(Date dateDebut,Date dateFin,  int depot , String requet) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from DetailProdRes c where c.dateProduction between :dateDebut and :dateFin and c.employe.depot.id=:depot"+requet);
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				
				return query.list();
	}


	public List<DetailProdRes> ListHeursDetailResponsableProdParDepotParJour(Date dateDebut,Date dateFin,  int depot, String matricule) {
		
		// TODO Auto-generated method stub
		Query query= null;
		if(matricule.equals(""))
		{
			
				query= session.createQuery("select c from DetailProdRes c where c.dateProduction between :dateDebut and :dateFin and c.employe.depot.id=:depot ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("depot", depot);
				
		
			 
		}else
		{
			
				
				 query= session.createQuery("select c from DetailProdRes c where c.dateProduction between :dateDebut and :dateFin and c.employe.matricule=:matricule and c.employe.depot.id=:depot ");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);
					query.setParameter("depot", depot);
					query.setParameter("matricule", matricule);
		
			
		}
		
				
				return query.list();
	}
	
	
	
	
	

}
